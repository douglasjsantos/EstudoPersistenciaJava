package br.com.fiap.contatos.dao;

import br.com.fiap.contatos.model.Contato;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ContatoDao {

    private EntityManager em;

    public void salvar(Contato contato){
        em.persist(contato);
    }

    public ContatoDao(EntityManager em){
        this.em = em;
    }



    public void atualizar(Contato contato){
        em.merge(contato);
    }

    public void excluir(Contato contato){
        Contato contatoExcluir = em.find(Contato.class, contato.getId());
        em.remove(contatoExcluir);
    }

    public void consultarContatoPorId(Long id){
        Contato contatoConsulta = em.find(Contato.class, id);
        System.out.println(contatoConsulta.toString());

        if (contatoConsulta == null){
            System.out.println("Contato não encontrado!");
        } else {
            System.out.println("-----------Objeto localizado-----------");
            System.out.println(contatoConsulta.toString());
            System.out.println("-----------Objeto localizado-----------");
        }
    }


    public List<Contato> listarTodosOsContatos(){
        String consulta = "SELECT c FROM Contato c ORDER BY nome DESC";

        return em.createQuery(consulta).getResultList();
    }

    public List<Contato> listarContatosPorEmail(String emailProcurado){
        String consulta = "SELECT c FROM contatos c WHERE = :email";

        return em.createQuery(consulta, Contato.class)
                .setParameter("email", emailProcurado)
                .getResultList();
    }
}
