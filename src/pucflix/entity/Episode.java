package pucflix.entity;

import java.time.LocalDate;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import pucflix.aeds3.EntidadeArquivo;

public class Episode implements EntidadeArquivo {
    // Atributes
    private int id;
    private String name;
    private int season;
    private LocalDate releaseDate;
    private float durationTime;
    
    // Constructors
    public Episode(String name, int season, LocalDate releaseDate, float durationTime) {
        this(-1, name, season, LocalDate.now(), durationTime);
    }
    public Episode() {
        this(-1, "", -1, LocalDate.now(), 0F);
    }
    public Episode(int id, String name, int season, LocalDate releasDate, float durationTime) {
        this.id = id;
        this.name = name;
        this.season = season;
        this.releaseDate = releasDate;
        this.durationTime = durationTime;
    }

    // Gets and Sets
    // Id
    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }
    // Name
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    // Season
    public void setSeason(int season) {
        this.season = season;
    }
    public int getSeason() {
        return season;
    }
    // Release date
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
    public LocalDate getReleaseDate() {
        return releaseDate;
    }
    // Duration time
    public void setDurationTime(float durationTime) {
        this.durationTime = durationTime;
    }
    public float getDurationTime() {
        return durationTime;
    }

    // Return atributes
    public String toString() {
        return "\nID...................: " + this.id +
               "\nNome.................: " + this.name +
               "\nTemporada.............: " + this.season +
               "\nData de lançamento...: " + this.releaseDate +
               "\nDuração..............: " + this.durationTime;
    }

    // Primitive type to byte array
    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(this.id);
        dos.writeUTF(this.name);
        dos.writeInt(this.season);
        dos.writeInt((int)this.releaseDate.toEpochDay());
        dos.writeFloat(this.durationTime);
        return baos.toByteArray();
    }

    // From byte array to primitive type
    public void fromByteArray(byte[] b) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        DataInputStream dis = new DataInputStream(bais);
        this.id = dis.readInt();
        this.name = dis.readUTF();
        this.season = dis.readInt();
        this.releaseDate = LocalDate.ofEpochDay(dis.readInt());
        this.durationTime = dis.readFloat();
    }
}
