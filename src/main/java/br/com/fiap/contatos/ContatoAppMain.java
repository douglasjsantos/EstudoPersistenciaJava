package br.com.fiap.contatos;

import br.com.fiap.contatos.dao.Conexao;
import br.com.fiap.contatos.dao.ContatoDao;
import br.com.fiap.contatos.dao.TipoContatoDao;
import br.com.fiap.contatos.model.Contato;
import br.com.fiap.contatos.model.TipoContato;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class ContatoAppMain {
    public static void main(String[] args) {


        EntityManager em = Conexao.getEntityManager();

        cadastrar(em);
        //atualizar(em);
        //excluir(em);
        //consultarContatoPorId(em);
        //listarTodosOsContatos(em);
        //listarContatosPorEmail(em);
        consultarContatoPeloId(em);
    }

    private static void consultarContatoPeloId(EntityManager em) {
        TipoContatoDao tipoContatoDao = new TipoContatoDao(em);
        TipoContato tipocontadoBuscado = new TipoContato();
        tipocontadoBuscado.setId(3L);
        TipoContato tipoContatoEncontrado = new TipoContato();

        tipoContatoEncontrado = tipoContatoDao.buscarTipoContatoPeloId(tipocontadoBuscado);

        System.out.println(tipoContatoEncontrado.getTipo());
    }

    public static void listarContatosPorEmail(EntityManager em){

        ContatoDao contatoDao = new ContatoDao(em);

        List<Contato> contatos = contatoDao.listarContatosPorEmail("idalina@mail.com.br");

        for(Contato contato : contatos){
            System.out.println("---------------------------");
            System.out.println(contato.toString());
        }
        System.out.println("Fim dos registros");
    }

    public static void cadastrar(EntityManager em){

        TipoContato tipoContato = new TipoContato();
        tipoContato.setId(3L);
        //tipoContato.setTipo("Família");

        TipoContatoDao tipoContatoDao = new TipoContatoDao(em);

        em.getTransaction().begin();
        //tipoContatoDao.salvar(tipoContato);

        Contato c1 = new Contato();
        c1.setNome("Douglas Santos");
        c1.setEmail("douglas@email.com.br");
        c1.setDataNascimento(LocalDate.of(1999,9,2));
        c1.setTipoContato(tipoContato);

        ContatoDao contatoDao = new ContatoDao(em);

        contatoDao.salvar(c1);

        em.getTransaction().commit();
    }

    public static void atualizar(EntityManager em){
        Contato c1 = new Contato();
        c1.setId(9L);
        c1.setNome("Edineide R.");
        c1.setEmail("edineide.rosalina@fiap.com.br");
        c1.setDataNascimento(LocalDate.of(1989,2,15));

        ContatoDao contatoDao = new ContatoDao(em);

        em.getTransaction().begin();
        contatoDao.atualizar(c1);
        em.getTransaction().commit();
    }

    public static void excluir(EntityManager em){
        Contato c1 = new Contato();
        c1.setId(7L);


        ContatoDao contatoDao = new ContatoDao(em);

        em.getTransaction().begin();
        contatoDao.excluir(c1);
        em.getTransaction().commit();
    }

    public static void consultarContatoPorId(EntityManager em){

        ContatoDao contatoDao = new ContatoDao(em);

        em.getTransaction().begin();
        contatoDao.consultarContatoPorId(6L);
        em.getTransaction().commit();
    }

    public static void listarTodosOsContatos(EntityManager em){

        ContatoDao contatoDao = new ContatoDao(em);

        List<Contato> contatos = contatoDao.listarTodosOsContatos();

        for(Contato contato : contatos){
            System.out.println("---------------------------");
            System.out.println(contato.toString());
            System.out.println("---------------------------");
        }
        System.out.println("Fim dos registros");
    }


}
