import java.io.*;
import java.util.*;

public class HuffCompression {
    private static Map<Byte, String> huffmap = new HashMap<>();
    private static int bitLength;

    public static void compress(String src, String dst) {
        try (FileInputStream inStream = new FileInputStream(src);
             ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {

            byte[] temp = new byte[1024];
            int bytesRead;
            while ((bytesRead = inStream.read(temp)) != -1) {
                buffer.write(temp, 0, bytesRead);
            }
            byte[] b = buffer.toByteArray();

            byte[] huffmanBytes = createZip(b);

            try (ObjectOutputStream objectOutStream = new ObjectOutputStream(new FileOutputStream(dst))) {
                objectOutStream.writeInt(bitLength);
                objectOutStream.writeObject(huffmanBytes);
                objectOutStream.writeObject(huffmap);
            }

        } catch (IOException e) {
            System.err.println("Compression error: " + e.getMessage());
        }
    }

    private static byte[] createZip(byte[] bytes) {
        PriorityQueue<ByteNode> nodes = new PriorityQueue<>();

        Map<Byte, Integer> freqMap = new HashMap<>();
        for (byte b : bytes) {
            freqMap.put(b, freqMap.getOrDefault(b, 0) + 1);
        }

        for (Map.Entry<Byte, Integer> entry : freqMap.entrySet()) {
            nodes.add(new ByteNode(entry.getKey(), entry.getValue()));
        }

        while (nodes.size() > 1) {
            ByteNode left = nodes.poll();
            ByteNode right = nodes.poll();
            ByteNode parent = new ByteNode(null, left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;
            nodes.add(parent);
        }

        generateCodes(nodes.poll(), new StringBuilder());
        return zipBytes(bytes);
    }

    private static void generateCodes(ByteNode node, StringBuilder code) {
        if (node == null) return;
        if (node.data != null) {
            huffmap.put(node.data, code.toString());
            return;
        }
        generateCodes(node.left, new StringBuilder(code).append('0'));
        generateCodes(node.right, new StringBuilder(code).append('1'));
    }

    private static byte[] zipBytes(byte[] bytes) {
        StringBuilder bitString = new StringBuilder();
        for (byte b : bytes) {
            bitString.append(huffmap.get(b));
        }
        bitLength = bitString.length();
        int padding = (8 - (bitLength % 8)) % 8;
        bitString.append("0".repeat(padding));
        byte[] result = new byte[bitString.length() / 8];
        for (int i = 0; i < result.length; i++) {
            String byteStr = bitString.substring(i * 8, (i + 1) * 8);
            result[i] = (byte) Integer.parseInt(byteStr, 2);
        }
        return result;
    }

    public static void decompress(String src, String dst) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(src));
             FileOutputStream fos = new FileOutputStream(dst)) {

            int bitLength = ois.readInt();
            byte[] huffmanBytes = (byte[]) ois.readObject();

            @SuppressWarnings("unchecked")
            Map<Byte, String> codes = (Map<Byte, String>) ois.readObject();

            fos.write(decompress(huffmanBytes, codes, bitLength));

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Decompression error: " + e.getMessage());
        }
    }

    private static byte[] decompress(byte[] huffmanBytes, Map<Byte, String> codes, int bitLength) {
        StringBuilder bitString = new StringBuilder();
        for (byte b : huffmanBytes) {
            bitString.append(String.format("%8s", Integer.toBinaryString(b & 0xFF))
                .replace(' ', '0'));
        }
        String actualBits = bitString.substring(0, bitLength);
        Map<String, Byte> reverseMap = new HashMap<>();
        for (Map.Entry<Byte, String> entry : codes.entrySet()) {
            reverseMap.put(entry.getValue(), entry.getKey());
        }
        List<Byte> result = new ArrayList<>();
        StringBuilder currentCode = new StringBuilder();
        for (char bit : actualBits.toCharArray()) {
            currentCode.append(bit);
            if (reverseMap.containsKey(currentCode.toString())) {
                result.add(reverseMap.get(currentCode.toString()));
                currentCode.setLength(0);
            }
        }
        byte[] byteArray = new byte[result.size()];
        for (int i = 0; i < byteArray.length; i++) {
            byteArray[i] = result.get(i);
        }
        return byteArray;
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: java HuffCompression c <input> <output> or java HuffCompression d <input> <output>");
            return;
        }

        String command = args[0].trim().toLowerCase();
        String input = args[1];
        String output = args[2];

        switch (command) {
            case "c" -> {
                compress(input, output);
                System.out.println("Compressed " + input + " to " + output);
            }
            case "d" -> {
                decompress(input, output);
                System.out.println("Decompressed " + input + " to " + output);
            }
            default -> System.err.println("Invalid command. Use 'c' for compress or 'd' for decompress.");
        }
    }
}
