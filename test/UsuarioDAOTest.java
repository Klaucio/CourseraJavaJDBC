/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import manipulacaousuarios.Usuario;
import manipulacaousuarios.UsuarioDAOImpl;
import org.dbunit.Assertion;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class UsuarioDAOTest implements UsuarioDAO{
    
    JdbcDatabaseTester jdt;
    private UsuarioDAOImpl usuarioDAOImpl;
    
    public UsuarioDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        jdt = new JdbcDatabaseTester("org.postgresql.Driver","jdbc:postgresql://localhost/coursera", "postgres", "secret");
        FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
        jdt.setDataSet(loader.load("/inicio.xml"));
        jdt.onSetup();
        usuarioDAOImpl = new UsuarioDAOImpl();
    }
    
    @After
    public void tearDown() {
    }

    @Override
    @Test
    public void recuperar() {
        Usuario usuario = usuarioDAOImpl.recuperar("admin");
        assertNotNull(usuario);
        assertEquals("admin", usuario.getLogin());
    }

    @Override
    @Test
    public void adicionarPontos() {
        usuarioDAOImpl.adicionarPontos("admin", 2);
        Usuario usuario = usuarioDAOImpl.recuperar("admin");
        assertNotNull(usuario);
        assertEquals("admin", usuario.getLogin());
        assertEquals("5", usuario.getPontos());        
    }

    @Override
    @Test
    public void ranking() {
        List<Usuario> lista;
        lista = usuarioDAOImpl.ranking();
        assertEquals(3, lista.size());
        assertEquals("Claucio", lista.get(0).getLogin());    
    }

    @Override
    @Test
    public void inserir() {
        try{
            Usuario u = new Usuario();
            u.setLogin("login");
            u.setNome("Novo Nome");
            u.setEmail("mailnovo@gmail.com");
            u.setPontos("0");
            u.setSenha("secret");

            usuarioDAOImpl.inserir(u);

            IDataSet currentDataSet = jdt.getConnection().createDataSet();
            ITable currentTable = currentDataSet.getTable("USUARIO");

            FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
            IDataSet expectedDataSet = loader.load("/verifica.xml");
            ITable expectedTable = expectedDataSet.getTable("USUARIO");

            assertEquals(expectedTable, currentTable);
        } catch (Exception e) {
        
        }
    }
}
