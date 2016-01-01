package part8;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class AkashandtheAssignment1 {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		int n = readInt(), q = readInt();
		StringBuilder builder2 = new StringBuilder(readString());
		int[][] characters = new int[n][26];
		for (int i = 0; i < n; i++) {
			int temp = builder2.charAt(i) - 'a';
			for (int j = 0; j < 26; j++) {
				if (j == temp) {
					if (i == 0)
						characters[i][j] = 1;
					else
						characters[i][j] = characters[i - 1][j] + 1;
				} else {
					if (i != 0)
						characters[i][j] = characters[i - 1][j];
				}
			}
		}
		while (q-- != 0) {
			int l = readInt() - 1, r = readInt() - 1, k = readInt();
			int counts = 0;
			int j;
			for (j = 0; j < 26; j++) {
				if (l != 0)
					counts += characters[r][j] - characters[l - 1][j];
				else
					counts += characters[r][j];
				if (counts >= k)
					break;
			}
			if (j == 26) {
				builder.append("Out of range\n");
			} else {
				builder.append((char) ('a' + j) + "\n");
			}
		}
		out.println(builder);
		out.flush();
		out.close();
	}

	public static int read() throws IOException {
		if (numChar <= curChar) {
			curChar = 0;
			numChar = stream.read(buffer);
			if (numChar <= 0) {
				return -1;
			}
		}
		return buffer[curChar++];
	}

	public static long readLong() throws IOException, InputMismatchException {
		int c = read();
		if (c == -1)
			throw new IOException();
		while (isSpaceChar(c)) {
			c = read();
		}
		boolean negative = false;
		if (c == '-') {
			negative = true;
			c = read();
		}
		long res = 0;
		while (!isSpaceChar(c)) {
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += (c - '0');
			c = read();
		}
		if (negative)
			return -res;
		return res;
	}

	public static int readInt() throws IOException, InputMismatchException {
		return (int) readLong();
	}

	public static String readString() throws IOException {
		int c = read();
		if (c == -1)
			throw new IOException();
		while (isSpaceChar(c)) {
			c = read();
		}
		StringBuilder builder = new StringBuilder();
		while (!isSpaceChar(c)) {
			builder.append((char) c);
			c = read();
		}
		return builder.toString();
	}

	public static boolean isSpaceChar(int c) {
		return c == ' ' || c == '\n' || c == '\t' || c == '\r' || c == -1;
	}
}
