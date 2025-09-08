# Polynomial Constant Finder

This Java program computes the **constant term (c)** of a polynomial using **Lagrange interpolation** given multiple roots with values in different bases.

---

## Features

- Automatically reads **all JSON test cases** in the folder.
- Handles **any base** for the polynomial values (binary, octal, decimal, hexadecimal, etc.).
- Works with **very large numbers** using `BigInteger`.
- Computes the constant **without any external libraries**.

---

## File Structure

- `PolynomialConstant.java` → Main Java program.
- `testcase1.json` → Sample test case 1.
- `testcase2.json` → Sample test case 2.
- `.gitignore` → Ignores compiled `.class` files.
- `README.md` → This file.

---

## How to Run

1. **Clone the repository**
```bash
git clone https://github.com/vamshipeddi7/PolynomialConstant-Assignment.git
cd PolynomialConstant-Assignment
Compile the Java program

bash
Copy code
javac PolynomialConstant.java
Run the program

bash
Copy code
java PolynomialConstant
This will automatically process all .json files in the folder and print the constant c for each.

Sample Output
TestCase 1

pgsql
Copy code
testcase1.json -> Constant c = 3
TestCase 2

pgsql
Copy code
testcase2.json -> Constant c = -6290016743746469796
Example JSON Structure
json
Copy code
{
  "keys": {
    "n": 4,
    "k": 3
  },
  "1": { "base": "10", "value": "4" },
  "2": { "base": "2", "value": "111" },
  "3": { "base": "10", "value": "12" }
}
n → Number of roots provided.

k → Minimum number of roots required to solve the polynomial (k = degree + 1).

Each entry contains:

base → The base of the value.

value → The root value in that base.

