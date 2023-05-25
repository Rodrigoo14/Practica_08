package ejercicios;

public class Test {

	public static void main(String[] args) throws ItemDuplicated, ItemNotFound {
		Integer a = 1;
		BSTree<Integer> arbol = new BSTree<Integer>();
		arbol.insert(10);
		arbol.insert(8);
		arbol.insert(6);
		arbol.insert(40);
		arbol.insert(11);
		arbol.insert(4);
		arbol.insert(50);
		arbol.insert(a);
		//arbol.remove(1);
		
		BSTree<Integer> arbol2 = new BSTree<Integer>();
		arbol2.insert(15);
		arbol2.insert(10);
		arbol2.insert(1);
		arbol2.insert(30);
		arbol.inorden();
		System.out.println();
		System.out.println("En el arbol hay " + arbol.countNonLeafNodes() + " no hojas");
		System.out.println("El elemento 10 tiene una altura de: " + arbol.getNodeHeight(10));
		System.out.println(arbol);
		System.out.println("PREORDEN ITERATIVO");
		arbol.preordenIterativo();
		System.out.println();
		System.out.println("AREA DEL ARBOL");
		System.out.println(arbol.calcularArea());
		
		System.out.println("La area de los dos arboles son iguales?\n" + igualArea(arbol, arbol2));
		
		System.out.println("Elemento minimo\n" + arbol.minimo().data);
		System.out.println("Elemento maximo\n" + arbol.maximo().data);
	}

	 public static <E extends Comparable<E>> boolean igualArea(BSTree<E> tree1, BSTree<E> tree2) {
	        int area1 = tree1.calcularArea();
	        int area2 = tree2.calcularArea();

	        return area1 == area2;
	    }
	 
}
