package bancoInterface;

/**
 *
 * @author gabrielkr
 */
public class BancoDeDados {
    private static String host = "localhost";
    private static Integer port = 2006;
    /**
     * Função para inserir uma frase no banco de dados.
     * Este método possui 2 parâmetros.
     * String frase = É a frase que você deseja inserir no banco de dados.
     * Integer tipo = É o tipo da frase.
     * Exemplo:
     * BancoDeDados.inserir("Ola mundo",1);
     * Insere a frase "Ola mundo" no banco de dados do tipo 1 (Motivação).
     */
    public static void inserir(String frase, Integer tipo) throws Exception
    {
        Conexao con = Conexao.conectar(host, port);
        PacoteBD pac = new PacoteBD();

        pac.setAcao(1); // Ação inserir

        Frase obj[] = new Frase[1];
        obj[0].setFrase(frase);
        obj[0].setTipo(tipo);
        pac.setObj(obj);
        con.enviarPacote(pac);
        pac = con.aguardarPacote();
        con.desconectar();
    }
    
    /**
     * Função para alterar uma frase no banco de dados.
     * Você deve fornecer o id da frase que deseja fazer a alteração.
     * Este método possui 3 parâmetros.
     * Integer id = O id da frase que está no banco de dados que você deseja alterar.
     * String frase = A nova frase que você deseja colocar no lugar.
     * Integer tipo = O tipo dessa nova frase.
     * Exemplo:
     * BancoDeDados.alterar(4,"Tchau mundo!",2);
     * Altera a frase com o id 4 para "Tchau mundo!", e o tipo dela para 2 (Felicitações).
     */
    public static void alterar(Integer id, String frase, Integer tipo) throws Exception
    {
        Conexao con = Conexao.conectar(host, port);
        PacoteBD pac = new PacoteBD();

        pac.setAcao(2); // Ação alterar

        Frase obj[] = new Frase[1];
        obj[0].setId(id);
        obj[0].setFrase(frase);
        obj[0].setTipo(tipo);
        pac.setObj(obj);
        con.enviarPacote(pac);
        pac = con.aguardarPacote();
        con.desconectar();
    }
    
    /**
     * Função para excluir uma frase no banco de dados.
     * Você deve fornecer o id da frase que deseja excluir.
     * Este método possui 1 parâmetro.
     * Integer id = O id da frase do banco de dados que será excluida.
     * Valor de retorno: A função retorna true caso a frase exista ou false caso não exista
     * Exemplo:
     * if(BancoDeDados.excluir(1))
     *  System.out.println("Frase excluida com sucesso!");
     * else
     *  System.out.println("Frase não encontrada!");
     */
    public static Boolean exluir(Integer id) throws Exception
    {
        Conexao con = Conexao.conectar(host, port);
        PacoteBD pac = new PacoteBD();

        pac.setAcao(3); // Ação excluir

        Frase obj[] = new Frase[1];
        obj[0].setId(id);
        pac.setObj(obj);
        con.enviarPacote(pac);
        pac = con.aguardarPacote();
        con.desconectar();
        return (pac.getAcao()==1);
    }
    
    /**
     * Função para conultar uma frase no banco de dados.
     * Você deve fornecer o id da frase que deseja consultar.
     * Este método possui 1 parâmetro.
     * Integer id = O id da frase do banco de dados que será consultada.
     * Valor de retorno: A função retorna um objeto Frase caso a frase exista ou null caso não exista
     * Exemplo:
     * if(BancoDeDados.excluir(1))
     *  System.out.println("Frase excluida com sucesso!");
     * else
     *  System.out.println("Frase não encontrada!");
     */
    public static Frase consulta(Integer id) throws Exception
    {
        Conexao con = Conexao.conectar(host, port);
        PacoteBD pac = new PacoteBD();

        pac.setAcao(4); // Ação consultar

        Frase obj[] = new Frase[1];
        obj[0].setId(id);
        pac.setObj(obj);
        con.enviarPacote(pac);
        pac = con.aguardarPacote();
        con.desconectar();
        return pac.getObj()[0];
    }
    
    /**
     * Função para conultar todas as frases de um determinado tipo.
     * Você deve fornecer o tipo de frase que deseja consultar.
     * Este método possui 1 parâmetro.
     * Integer tipo = O tipo da frase do banco de dados que será consultada.
     * Valor de retorno: A função retorna um vetor de objeto Frase, contendo todas as frases do tipo solicitado.
     * Exemplo (exibe todas as frases do tipo 2 "Felicitações"):
     * Frase f[] = BancoDeDados.lista_tipo(2);
     * for(Frase atual: f)
     *      System.out.println("\nFrase: "+atual.getFrase());
     */
    public static Frase[] lista_tipo(Integer tipo) throws Exception
    {
        Conexao con = Conexao.conectar(host, port);
        PacoteBD pac = new PacoteBD();

        pac.setAcao(5); // Ação lista_tipo

        Frase obj[] = new Frase[1];
        obj[0].setTipo(tipo);
        pac.setObj(obj);
        con.enviarPacote(pac);
        pac = con.aguardarPacote();
        con.desconectar();
        return pac.getObj();
    }
    
    /**
     * Função que retorna uma frase aleatória de um determinado tipo.
     * Você deve fornecer o tipo de frase que deseja consultar.
     * Este método possui 1 parâmetro.
     * Integer tipo = O tipo da frase do banco de dados que será consultada.
     * Valor de retorno: A função retorna um objeto Frase contendo uma frase aleatoria do banco de dados.
     * Exemplo (exibe uma frase aleatória do tipo 2 "Felicitações"):
     * Frase f = BancoDeDados.mensagem(2);
     * System.out.println("Frase aleatória: "+f.getFrase());
     */
    public static Frase mensagem(Integer tipo) throws Exception
    {
        Conexao con = Conexao.conectar(host, port);
        PacoteBD pac = new PacoteBD();

        pac.setAcao(6); // Ação lista_tipo

        Frase obj[] = new Frase[1];
        obj[0].setTipo(tipo);
        pac.setObj(obj);
        con.enviarPacote(pac);
        pac = con.aguardarPacote();
        con.desconectar();
        return pac.getObj()[0];
    }

}
