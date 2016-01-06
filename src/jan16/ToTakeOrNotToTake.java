package jan16;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

public class ToTakeOrNotToTake {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		long t = readInt();
		while (t-- != 0) {
			long n = readInt();
			long max = 1;
			long min = 1;

			for (long i = 0; i < n; i++) {
				char ch = readString().charAt(0);
				long temp = 0;
				if (ch != 'N')
					temp = readLong();
				Vector<Long> integers = new Vector<>();
				integers.add(max);
				integers.add(min);
				integers.add(find(max, ch, temp));
				integers.add(find(min, ch, temp));
				Collections.sort(integers);
				min = integers.get(0);
				max = integers.get(integers.size() - 1);
			}
			builder.append(max + "\n");
		}
		out.println(builder);
		out.flush();
		out.close();
	}

	public static long find(long number, char chars, long temp) {
		if (chars == 'N')
			return -number;
		else if (chars == '-')
			return number - temp;
		else if (chars == '/')
			return number / temp;
		else if (chars == '*')
			return temp * number;
		else
			return temp + number;
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
