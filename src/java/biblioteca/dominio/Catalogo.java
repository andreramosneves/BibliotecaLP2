/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.dominio;

import java.util.List;

/**
 *
 * @author 41445368
 */
public class Catalogo {

    private List<Livro> livros;
    private List<Revista> revistas;

    public Catalogo() {
    }

    public Catalogo(List<Livro> livros, List<Revista> revistas) {
        this.livros = livros;
        this.revistas = revistas;
    }

    public void addLivro(Livro livro) {
        livros.add(livro);
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public List<Revista> getRevistas() {
        return revistas;
    }

    public void setRevistas(List<Revista> revistas) {
        this.revistas = revistas;
    }

    public void addRevista(Revista revista) {
        revistas.add(revista);
    }

}
