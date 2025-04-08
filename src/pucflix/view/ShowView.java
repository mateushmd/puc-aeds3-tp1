package pucflix.view;

import pucflix.entity.Show;
import pucflix.model.ShowFile;
import java.lang.NumberFormatException;

public class ShowView extends View 
{
    private ShowFile file; 

    public ShowView(Prompt prompt, ShowFile file) throws Exception
    {
        super(prompt);
        this.file = file;
    }

    @Override
    public String getName()
    {
        return "Séries";
    }

    @Override
    public String getPrompt(int depth)
    {
        return
            "1) Incluir\n" +
            "2) Buscar\n" +
            "3) Alterar\n" +
            "4) Excluir";
    }

    @Override
    public void eval(int input, int depth) throws Exception
    {
        switch(input)
        {
            case 1:
            {
                String name = prompt.askForInput("Nome: ");
                int releaseYear = Integer.parseInt(prompt.askForInput("Ano de lançamento: "));
                String sinopsys = prompt.askForInput("Sinopse: ");
                String streaming = prompt.askForInput("Serviço de streaming: ");
                Show show = new Show(name, releaseYear, sinopsys, streaming); 
                file.create(show);
                System.out.println("Operação finalizada com sucesso");
                break;
            }
            case 2:
            {
                String search = prompt.askForInput("Busca: ");
                Show[] shows = file.read(search);

                if(shows == null || shows.length == 0)
                    System.out.println("Nenhuma série encontrada");

                if(shows.length == 1)
                {
                    System.out.println(shows[0].toString());
                    break;
                }
                    
                String result = "";
                
                for(int i = 0; i < shows.length; i++)
                    result += (i + 1) + ") " + shows[i].getName() + "\n"; 

                System.out.println(result);

                int n = 0;
                boolean valid = false;

                while(!valid)
                {
                    try
                    {
                        n = Integer.parseInt(prompt.askForInput("Número: "));
                        if(n < 1 || n > shows.length) throw new Exception();
                        valid = true;
                        n--;
                    }
                    catch(Exception ex) { System.out.println("Insira um número válido"); }
                }

                System.out.println(shows[n].toString());
                
                break;
            }
            case 3: 
            {
                String search = prompt.askForInput("Nome: ");
                
                Show[] shows = file.read(search);
                int n = 0;

                while(shows == null)
                {
                    search = prompt.askForInput("Nenhuma série encontrada, tente novamente: "); 
                    shows = file.read(search);
                }

                if(shows.length > 1)
                {
                    for(int i = 0; i < shows.length; i++)
                        System.out.println((i + 1) + ") " + shows[i].getName());

                    boolean valid = false;
                    while(!valid)
                    {
                        try
                        {
                            n = Integer.parseInt(prompt.askForInput("Diversas séries encontradas, escolha uma: "));
                            if(n < 1 || n > shows.length) throw new Exception();
                            valid = true;
                            --n;
                        }
                        catch(Exception ex)
                        { System.out.println("Insira um número válido"); }
                    }
                }

                Show show = shows[n];

                String name = prompt.askForInput("Nome (vazio para não alterar): ");
                if(!name.isEmpty())
                    show.setName(name);
                    
                String strYear = prompt.askForInput("Ano de lançamento (vazio para não alterar): ");
                if(!strYear.isEmpty())
                    show.setReleaseYear(Integer.parseInt(strYear));

                String synopsis = prompt.askForInput("Sinopse (vazio para não alterar): ");
                if(!synopsis.isEmpty())
                    show.setSynopsis(synopsis);

                String streaming = prompt.askForInput("Serviço de streaming (vazio para não alterar): ");
                if(!streaming.isEmpty())
                    show.setStreamingService(streaming);

                file.update(show);

                System.out.println("Operação finalizada com sucesso");
                break;
            }
            case 4:
            {
                String search = prompt.askForInput("Nome: ");
                
                Show[] shows = file.read(search);
                int n = 0;

                while(shows == null)
                {
                    search = prompt.askForInput("Nenhuma série encontrada, tente novamente: "); 
                    shows = file.read(search);
                }

                if(shows.length > 1)
                {
                    for(int i = 0; i < shows.length; i++)
                        System.out.println((i + 1) + ") " + shows[i].getName());

                    boolean valid = false;
                    while(!valid)
                    {
                        try
                        {
                            n = Integer.parseInt(prompt.askForInput("Diversas séries encontradas, escolha uma: "));
                            if(n < 1 || n > shows.length) throw new Exception();
                            valid = true;
                            --n;
                        }
                        catch(Exception ex)
                        { System.out.println("Insira um número válido"); }
                    }
                }

                int id = shows[n].getID(); 
                
                if(!file.delete(id))
                    System.out.println("A série não pode ser excluída pois está vinculada à episódios");
                else 
                    System.out.println("Operação finalizada com sucesso");
                break;
            }
        }
    }
}
