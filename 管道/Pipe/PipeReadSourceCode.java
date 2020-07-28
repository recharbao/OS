package Pipe;

//下面为PipeReader中read带参数部分的源代码:
/*
public synchronized int read(char cbuf[], int off, int len)  throws IOException {
        if (!connected) {
        throw new IOException("Pipe not connected");
        } else if (closedByReader) {
        throw new IOException("Pipe closed");
        } else if (writeSide != null && !writeSide.isAlive()
        && !closedByWriter && (in < 0)) {
        throw new IOException("Write end dead");
        }

        if ((off < 0) || (off > cbuf.length) || (len < 0) ||
        ((off + len) > cbuf.length) || ((off + len) < 0)) {
        throw new IndexOutOfBoundsException();
        } else if (len == 0) {
        return 0;
        }


        int c = read();
        if (c < 0) {
        return -1;
        }
        cbuf[off] =  (char)c;
        int rlen = 1;
        while ((in >= 0) && (--len > 0)) {
        cbuf[off + rlen] = buffer[out++];
        rlen++;
        if (out >= buffer.length) {
        out = 0;
        }
        if (in == out) {

        in = -1;
        }
        }
        return rlen;
        }
        */

