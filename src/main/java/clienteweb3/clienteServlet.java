package clienteweb3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fabrica.model.Cliente;
import br.com.fabrica.service.ClienteService;

@WebServlet(urlPatterns = {"/cliente" , "/clienteServlet"})

public class clienteServlet extends HttpServlet{
	
	ClienteService clienteservice;
	
	public clienteServlet() {
		System.out.println("contruindo...");
	}
	
	@Override
    public void init() throws ServletException {
    	clienteservice = new ClienteService();
    	System.out.println("inicializando");
    	super.init();
    }
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("chamando o service");
		super.service(req, resp);
	}
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	Cliente cli = new Cliente();
    	cli.setEmail("");
    	String i = req.getParameter("i");
    	String acao = req.getParameter("acao");
    	if(i!=null && i!="" && acao!=null && acao!="")
			if(acao.equals("exc")) {
    			clienteservice.excluir(Integer.parseInt(i));
    		}else if (acao.equals("edit")) {
    			int indice = Integer.parseInt(i);
    			 cli = clienteservice.buscarPorIndice(indice);
    		}
    	
    	
    	RequestDispatcher dispatcher = req.getRequestDispatcher("cliente.jsp");
    	req.setAttribute("cli", cli);
    	req.setAttribute("iCli", i);
    	req.setAttribute("lista", clienteservice.getTodosClientes());
    	
    	dispatcher.forward(req, resp);
    }
    
    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	//recebendo email
    	String email = req.getParameter("email");
    	
    	//colocando email em um objeto cliente
    	Cliente cli = new Cliente();
    	cli.setEmail(email);
    	
    	
    	
    	clienteservice.cadastrar(cli);
    	
    	cli = new Cliente();
    	cli.setEmail("");		
    	//adicionando o objeto cliente na lista de cliente
    	
        RequestDispatcher dispatcher = req.getRequestDispatcher("cliente.jsp");
    	
        req.setAttribute("msg", "Cadastrado com sucesso!");
        req.setAttribute("cli", cli);
        
    	req.setAttribute("lista", clienteservice.getTodosClientes());
    	
    	dispatcher.forward(req, resp);
    	
    	//System.out.println("do post");
    	resp.setCharacterEncoding("UTF-8");
    	resp.getWriter().print("chamou pelo método post " + email +"!");
    }
}
