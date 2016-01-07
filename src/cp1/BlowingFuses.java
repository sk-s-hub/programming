package cp1;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class BlowingFuses {
	static int numChar;
	static int curChar;
	static byte[] buffer = new byte[1024];
	static InputStream stream;
	static PrintWriter out;

	public static void main(String[] args) throws InputMismatchException, IOException {
		stream = System.in;
		out = new PrintWriter(new BufferedOutputStream(System.out));
		StringBuilder builder = new StringBuilder();
		int t = 0;
		while (true) {
			long n = readLong(), m = readLong(), capacity = readLong();
			if (n == 0)
				break;
			t++;
			builder.append("Sequence " + t + "\n");
			boolean[] switchBaby = new boolean[(int) (n + 1)];
			long[] switchCapacity = new long[(int) n + 1];
			long ans = 0;
			boolean found = false;
			for (int i = 1; i <= n; i++) {
				switchCapacity[i] = readLong();
			}
			long max = Long.MIN_VALUE;
			for (int i = 0; i < m; i++) {
				long temp = readLong();
				if (!switchBaby[(int) temp]) {
					switchBaby[(int) temp] = true;
					ans += switchCapacity[(int) temp];
					max = Math.max(max, ans);
					if (ans > capacity)
						found = true;
				} else {
					switchBaby[(int) temp] = false;
					ans -= switchCapacity[(int) temp];
				}
			}
			if (found) {
				builder.append("Fuse was blown.\n");
			} else {
				builder.append("Fuse was not blown.\n");
				builder.append("Maximal power consumption was " + max + " amperes.\n");
			}
			builder.append("\n");
		}
		out.print(builder);
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