package pucflix.model;

import pucflix.entity.Episode;
import pucflix.aeds3.Arquivo;
import pucflix.aeds3.HashExtensivel;
import pucflix.aeds3.ArvoreBMais;

public class EpisodeFile extends Arquivo<Episode> 
{
    HashExtensivel<EpisodeNameIdPair> nameIndex;    

    public EpisodeFile() throws Exception
    {
        super("episodes", Episode.class.getConstructor());
        nameIndex = new HashExtensivel<>(
            EpisodeNameIdPair.class.getConstructor(),
            4,
            "./dados/" + nomeEntidade + "/nameIndex.d.db",
            "./dados/" + nomeEntidade + "/nameIndex.c.db"
        );
    }

    @Override
    public void create(Episode episode) throws Exception 
    {
        int id = super.create(episode);
        nameIndex.create(new EpisodeNameIdPair(episode.getName(), id));
        return id;
    }

    @Override
    public void update(Episode episode) 
    {
        Episode s = read(episode.getId());

        if(s == null) return false;
        if(!super.update(episode)) return false; 
        
        if(!s.getName().equals(episode.getName()))
        {
            nameIndex.delete(new EpisodeNameIdPair(s.getName(), s.getId()));
            nameIndex.create(new EpisodeNameIdPair(episode.getName(), episode.getId()));
        }

        return true;
    }

    public Episode[] read(String name) throws Exception
    {
        if(name.isEmpty()) return null;

        ArrayList<EpisodeNameIdPair> pairs = nameIndex.read(new EpisodeNameIdPair(name, -1));
        if(pairs.size() == 0) return null;

        Episode[] episode = new Episode[pairs.size()];
        
        for(int i = 0; i < pairs.size(); i++)
        {
            episode[i] = read(pairs.getId());
        }
    }

    @Override
    public boolean delete(int id) throws Exception 
    {
        Episode episode = read(id);
        
        if(episode == null) return false;
        if(!super.delete(id)) return false;
        return nameIndex.delete(new EpisodeNameIdPair(episode.getName(), id));
    }

    public boolean delete(String name) throws Exception
    {
        if(name.isEmpty())
            return false;

        ArrayList<EpisodeNameIdPair> pairs = nameIndex.read(new EpisodeNameIdPair(name, -1));
        for(EpisodeNameIdPair pair : pairs)
        {
            if(pair.getName().equals(name))
                return delete(pair.getId);
        }

        return false;
    }
}