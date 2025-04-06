public class Proceso{
    String id_Proceso;
    String nombre;
    int tamano;
    int tEjecucion;
    int tLlegada;
    int tiempoRestante;

    public Proceso(String id, String nombre, int tamano, int ejecucion, int llegada){
        this.id_Proceso=id;
        this.nombre=nombre;
        this.tamano=tamano;
        this.tEjecucion=ejecucion;
        this.tLlegada=llegada;
        this.tiempoRestante=ejecucion;
    }
}

class Nodo{
    Proceso proceso;
    Nodo nodoSiguiente;

    public Nodo(Proceso proceso){
        this.proceso=proceso;
        this.nodoSiguiente=null;
    }
}

class ListaDinamica{
    Nodo head;
    Nodo tail;

    public ListaDinamica(){
        this.head=null;
        this.tail=null;
    }

    public void agregar(Proceso proceso){
        Nodo nuevoNodo=new Nodo(proceso);
        if (head==null){
            head=nuevoNodo;
            tail=nuevoNodo;
        }else{
            tail.nodoSiguiente=nuevoNodo;
            tail=nuevoNodo;
        }
    }

    public void imprimir() {
        Nodo temp=head;
        while (temp!=null) {
            Proceso p=temp.proceso;
            System.out.println("\tId: " + p.id_Proceso + ", nombre: " + p.nombre +
                               ", tamaño: " + p.tamano +
                               ", tiempo ejecución: " + p.tEjecucion +
                               ", tiempo llegada: " + p.tLlegada+
                               ", tiempo restante: " + p.tiempoRestante);
            temp=temp.nodoSiguiente;
        }
    }

    public int verificarId(String id){
        Nodo temp=head;
        while (temp!=null){
            String idCreado=temp.proceso.id_Proceso;
            if (id.equals(idCreado)){
                return 1;
            } 
            temp=temp.nodoSiguiente;
        }
        return 0;
    }

    public void eliminar(String mensaje){
        if (head==null){
            System.out.println("La cola de " + mensaje + " está vacía");
        }else if (head==tail){
            System.out.println("\nSe eliminó el proceso " + head.proceso.nombre + " de la cola de " + mensaje);
            head=null;
            tail=null;
            System.out.println("Cola de procesos listos:");
            System.out.println("\tLa cola de " + mensaje + " está vacía");
        }
        else{
            System.out.println("\nSe eliminó el proceso " + head.proceso.nombre + " de la cola de " + mensaje);
            head=head.nodoSiguiente;
            System.out.println("Cola de procesos listos:");
            this.imprimir();
        }
    }
}