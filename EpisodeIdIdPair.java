package pucflix.model;

import pucflix.aeds3.RegistroArvoreBMais;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class EpisodeIdIdPair implements RegistroArvoreBMais<EpisodeIdIdPair> {
    private int id;
    private int showId;

    public EpisodeIdIdPair() throws Exception
    {
        this(-1, -1);
    }

    public EpisodeIdIdPair(int id) throws Exception
    {
        this(id, -1);
    }

    public EpisodeIdIdPair(int id, int showId) throws Exception
    {
        this.id = id;
        this.showId = showId;
    }

    public int getId() { return id; }
    public int getShowId() { return showId; }

    @Override
    public EpisodeIdIdPair clone()
    {
        try { return new EpisodeIdIdPair(id, showId); }
        catch(Exception e) { e.printStackTrace(); }

        return null;
    }

    public int compareTo(EpisodeIdIdPair other)
    {
        if(showId != other.showId)
        {
            return this.id - other.id;
        }

        return showId - other.showId;
    }

    public byte[] toByteArray() throws IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        dos.writeInt(id);
        dos.writeInt(showId);
        return baos.toByteArray();
    }

    public void fromByteArray(byte[] buffer) throws IOException
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
        DataInputStream dis = new DataInputStream(bais);

        id = dis.readInt();
        showId = dis.readInt();
    }
}
