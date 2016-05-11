/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.servlet;

import biblioteca.dao.api.ControllerInterface;
import biblioteca.dao.api.ControllerInterface.ReturnType;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tomaz
 */
public class ControladorFrente extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ControllerInterface controle = null;
        // obter instância do controlador
        String acao = request.getParameter("acao");

        switch (acao) {
            case "autentica":
                request.getRequestDispatcher("ListaOpcoes.html").forward(request, response);
                return;
            case "listarUsuario":
                controle = new ControladorListaUsuario();
                break;
            case "listarEmprestimo":
                controle = new ControladorListaEmprestimo();
                break;
            case "listarPublicacao":
                controle = new ControladorListaPublicacao();
                break;
            case "listarLivro":
                controle = new ControladorListaPublicacao();
                break;
            case "ControladorEdicaoUsuario":
                controle = new ControladorEdicaoUsuario(Integer.parseInt(request.getParameter("id")));
                break;
            case "ControladorAtualizaUsuario":
                controle = new ControladorAtualizaUsuario();
                break;
            case "ControladorAtualizaEmprestimo":
                controle = new ControladorAtualizaEmprestimo();
                break;
            case "ControladorAdicionarUsuario":
                controle = new ControladorAdicionarUsuario();
                break;
            case "ControladorAdicionarEmprestimo":
                controle = new ControladorAdicionarEmprestimo();
                break;
            case "ControladorAdicionarLivro":
                controle = new ControladorAdicionarLivro();
                break;
            case "ControladorAdicionarItemEmprestimo":
                controle = new ControladorAdicionarItemEmprestimo();
                break;
            case "ControladorCarregaEmprestimo":
                controle = new ControladorCarregaEmprestimo();
                break;
            case "ControladorCarregaItemEmprestimo":
                controle = new ControladorCarregaItemEmprestimo();
                break;

            case "ControladorEdicaoEmprestimo":
                controle = new ControladorEdicaoEmprestimo();
                break;
            case "ControladorRemocaoUsuario":
                controle = new ControladorRemocaoUsuario();
                break;
            case "ControladorRemocaoEmprestimo":
                    controle = new ControladorRemocaoEmprestimo();
                break;
            case "ControladorRemocaoLivro":
                controle = new ControladorRemocaoLivro();
                break;
            case "ControladorRemocaoItem":
                controle = new ControladorRemocaoItem();
                break;
            default:
                request.getRequestDispatcher("ListaOpcoes.html").forward(request, response);
                return;

        }

        // inicialização
        controle.init(request);

        // execução da manipulação de dados
        controle.execute();

        // encaminhamento para próxima página
        if (controle.getReturnType() == ReturnType.FORWARD) {
            RequestDispatcher rd;
            rd = request.getRequestDispatcher(controle.getReturnPage());
            rd.forward(request, response);
        } else if (controle.getReturnType() == ReturnType.REDIRECT) {
            response.sendRedirect(controle.getReturnPage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
