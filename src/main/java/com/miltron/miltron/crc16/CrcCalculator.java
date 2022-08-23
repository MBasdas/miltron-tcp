package com.miltron.miltron.crc16;

public class CrcCalculator {

    private final AlgoParams parameters;
    public byte HashSize = 8;
    private long _mask = 0xFFFFFFFFFFFFFFFFL;
    private final long[] _table = new long[256];

    public CrcCalculator(AlgoParams params)
    {
        parameters = params;

        HashSize = (byte) params.HashSize;
        if (HashSize < 64)
        {
            _mask = (1L << HashSize) - 1;
        }

        CreateTable();
    }

    public long Calc(byte[] data, int offset, int length)
    {
        long init = parameters.RefOut ? CrcHelper.ReverseBits(parameters.Init, HashSize) : parameters.Init;
        long hash = ComputeCrc(init, data, offset, length);
        return (hash ^ parameters.XorOut) & _mask;
    }

    private long ComputeCrc(long init, byte[] data, int offset, int length)
    {
        long crc = init;

        if (parameters.RefOut)
        {
            for (int i = offset; i < offset + length; i++)
            {
                crc = (_table[(int)((crc ^ data[i]) & 0xFF)] ^ (crc >>> 8));
                crc &= _mask;
            }
        }
        else
        {
            int toRight = (HashSize - 8);
            toRight = toRight < 0 ? 0 : toRight;
            for (int i = offset; i < offset + length; i++)
            {
                crc = (_table[(int)(((crc >> toRight) ^ data[i]) & 0xFF)] ^ (crc << 8));
                crc &= _mask;
            }
        }

        return crc;
    }

    private void CreateTable()
    {
        for (int i = 0; i < _table.length; i++)
            _table[i] = CreateTableEntry(i);
    }

    private long CreateTableEntry(int index)
    {
        long r = (long)index;

        if (parameters.RefIn)
            r = CrcHelper.ReverseBits(r, HashSize);
        else if (HashSize > 8)
            r <<= (HashSize - 8);

        long lastBit = (1L << (HashSize - 1));

        for (int i = 0; i < 8; i++)
        {
            if ((r & lastBit) != 0)
                r = ((r << 1) ^ parameters.Poly);
            else
                r <<= 1;
        }

        if (parameters.RefOut)
            r = CrcHelper.ReverseBits(r, HashSize);

        return r & _mask;
    }

    public AlgoParams getParams(){
        return parameters;
    }
}