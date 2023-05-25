package actividades;

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
		arbol.inorden();
		System.out.println();
		arbol.remove(1);
		
		arbol.inorden();
		System.out.println();
		System.out.println(arbol);


	}

}
