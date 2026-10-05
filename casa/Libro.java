public class Libro{
    private String titulo;
    private String autor;
    private String ISBN;
    private int numPaginas;
    public Libro(String titulo, String autor, String ISBN, int numPaginas){
        this.titulo = titulo;
        this.autor = autor;
        this.ISBN = ISBN;
        this.numPaginas = numPaginas;
    }
    public void mostrarInfo(){
        System.out.println("Nombre del libro    : " + this.titulo);
        System.out.println("Nombre del autor    : " + this.autor);
        System.out.println("ISBN                : " + this.ISBN);
        System.out.println("Numero de paginas   : " + this.numPaginas);
    }
}