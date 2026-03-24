
# 🗜️ File Compression Tool (Huffman Encoding)

A Java-based file compression and decompression tool built using **Huffman Coding**, supporting both **command-line execution and GUI interaction**.

This project demonstrates strong understanding of **Data Structures, Algorithms, and File Handling**, applied to a real-world problem.

---

## 🚀 Features

* 📦 Compress files using Huffman Encoding
* 🔓 Decompress files back to original form
* ⚡ Efficient binary encoding to reduce file size
* 🧠 Custom implementation of Huffman Tree (Greedy Algorithm)
* 💻 Command Line Interface (CLI) support
* 🖥️ Optional GUI for ease of use
* 📁 Works with real file inputs

---

## 🛠️ Tech Stack

* **Language:** Java
* **Core Concepts:**

  * Greedy Algorithms (Huffman Coding)
  * Binary Trees
  * Priority Queue (Min Heap logic)
  * File I/O Streams
* **UI:** Java Swing

---

## ⚙️ How It Works

1. Reads input file
2. Calculates frequency of each character
3. Builds Huffman Tree using Min Heap
4. Generates unique binary codes
5. Compresses file into `.texthuff` format
6. Stores data required for decompression
7. Decodes back to original file

---

## 💻 Command Line Usage

### 🔹 Compress a File

```bash
java HuffCompression c text.txt text.tex.thuff
```

* `c` → Compress mode
* `text.txt` → Input file
* `text.texthuff` → Output compressed file

---

### 🔹 Decompress a File

```bash
java HuffCompression d text.text.huff output.txt
```

* `d` → Decompress mode
* `text.texthuff` → Compressed file
* `output.txt` → Restored file

---

## 📂 Project Structure

* `HuffCompression.java` → Core logic (CLI execution entry point)
* `EncoderGUI.java` → Graphical interface
* `ByteNode.java / CharNode.java` → Node structures
* `CharLinkedList.java` → Character storage
* `Displayer.java` → Output handling

---

## ▶️ How to Run

1. Clone the repository

   ```bash
   git clone https://github.com/justt-ashy/File-Compression-Tool.git
   ```

2. Navigate to project folder

   ```bash
   cd File-Compression-Tool
   ```

3. Compile:

   ```bash
   javac *.java
   ```

4. Run (CLI mode):

   ```bash
   java HuffCompression c input.txt output.text.huff
   ```

---

## 📊 Example Use Case

* Reduce file size for storage optimization
* Demonstrate compression techniques
* Learn practical use of greedy algorithms

---

## 💡 Key Learnings

* Implemented Huffman Coding from scratch
* Handled binary file operations in Java
* Designed both CLI and GUI interfaces
* Bridged DSA concepts with real-world application

---

## 🧠 Why This Project Stands Out

* Shows **deep understanding of Greedy Algorithms**
* Combines **DSA + System Implementation**
* Demonstrates ability to build **usable tools (CLI + GUI)**
* Reflects **problem-solving + practical engineering skills**

---

## 🔮 Future Improvements

* Support for images and other file formats
* Compression ratio optimization
* Add performance metrics
* Convert to cross-platform executable

---

## 👤 Author

**Ashy**
B.Tech CSE | Java Backend Developer | DSA Enthusiast

---

## ⭐ Support

If you found this useful, consider giving it a star ⭐
