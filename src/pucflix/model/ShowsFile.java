package pucflix.model;

import pucflix.entity.Show;
import pucflix.aeds3.Arquivo;
import pucflix.aeds3.HashExtensivel;
import pucflix.aeds3.ArvoreBMais;

public class ShowsFile extends Arquivo<Show> 
{
    HashExtensivel<ShowNameIdPair> nameIndex;    

    public ShowsFile() throws Exception
    {
        super("shows", Show.class.getConstructor());
        nameIndex = new HashExtensivel<>(
            ShowNameIdPair.class.getConstructor(),
            4,
            "./dados/" + nomeEntidade + "/nameIndex.d.db",
            "./dados/" + nomeEntidade + "/nameIndex.c.db"
        );
    }

    @Override
    public void create(Show show) throws Exception 
    {
        int id = super.create(show);
        nameIndex.create(new ShowNameIdPair(show.getName(), id));
        return id;
    }

    @Override
    public void update(Show show) 
    {
        Show s = read(show.getId());

        if(s == null) return false;
        if(!super.update(show)) return false; 
        
        if(!s.getName().equals(show.getName()))
        {
            nameIndex.delete(new ShowNameIdPair(s.getName(), s.getId()));
            nameIndex.create(new ShowNameIdPair(show.getName(), show.getId()));
        }

        return true;
    }

    public Show[] read(String name) throws Exception
    {
        if(name.isEmpty()) return null;

        ArrayList<ShowNameIdPair> pairs = nameIndex.read(new ShowNameIdPair(name, -1));
        if(pairs.size() == 0) return null;

        Show[] shows = new Show[pairs.size()];
        
        for(int i = 0; i < pairs.size(); i++)
        {
            shows[i] = read(pairs.getId());
        }
    }

    @Override
    public boolean delete(int id) throws Exception 
    {
        Show show = read(id);
        
        if(show == null) return false;
        if(!super.delete(id)) return false;
        return nameIndex.delete(new ShowNameIdPair(show.getName(), id));
    }

    public boolean delete(String name) throws Exception
    {
        if(name.isEmpty())
            return false;

        ArrayList<ShowNameIdPair> pairs = nameIndex.read(new ShowNameIdPair(name, -1));
        for(ShowNameIdPair pair : pairs)
        {
            if(pair.getName().equals(name))
                return delete(pair.getId);
        }

        return false;
    }
}
