package model;

import aeds3.*;

public class EpisodeFile extends aeds3.File<Episode> 
{

    File<Episode> fileEpisode;
    ExtensiveHash<ParNameId> indirectIndexName;

    public EpisodeFile()
    {
        name = "Episódios";
    }

    // Create Episode
    @Override
    public void create(Episode e) throws Exception{
        int id = super.create(e);
        indirectIndexName.create(new parNameId(e.getName(), id))
    }

    // Read Episode
    @Override
    public void read() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'read'");
    }

    // Update Episode
    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    // Delete Episode
    @Override
    public void delete(int id) throws Exception{
        Episode e = super.read(id);
        if(e != null) {
            if(super.delete(id)) {
                return indirectIndexId.delete(ParNameId.hash(e.getName));
            }
        }
        return false;
    }
}
