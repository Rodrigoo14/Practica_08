package ejercicios;

import java.util.Stack;

public class BSTree<E extends Comparable<E>> {
	//Clase Node
	class Node {
		protected E data;
		protected Node left;
		protected Node right;
		
		public Node(E data) {
			this(data, null, null);
		}
		
		public Node(E data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
		
		@Override
		public String toString() {
			String s = this.data.toString() + " ";
			return s;
		}
	}
	
	//Atributos de la clase
	private Node root;
	
	//Metodo Constructor
	public BSTree() {
		this.root = null;
	}
	
	public boolean isEmpty() {
		return this.root == null;
	}
	
	//Metodo que inserta un elemento al BST
	//Si el elemento existe en el arbol, levanta la excepcion ItemDuplicated
	public void insert(E x) throws ItemDuplicated {
		Node nuevo = new Node(x);
		if (this.isEmpty()) {
			this.root = nuevo;
		} else {
			Node aux = this.root;
			Node padre;
			while (aux != null) {
				padre = aux;
				if (aux.data.compareTo(x) < 0) {
					aux = aux.left;
					if (aux == null) {
						padre.left = nuevo;
						break;
					}
				}
				else if (aux.data.compareTo(x) > 0) {
					aux = aux.right;
					if (aux == null) {
						padre.right = nuevo;
						break;
					}
				}
				else {
					throw new ItemDuplicated("No se puede agregar el elemento ITEM duplicado");
				}
				
			}			
		}
	}
	
	public E search(E x) throws ItemNotFound {
		Node res = searchRec(x, this.root);
		if (res == null) {
			throw new ItemNotFound("El dato "+ x + " no esta.");
		}
		return res.data;
	}
	
	protected Node searchRec(E x, Node n) {
		if (n == null) {
			return null;
		}
		else {
			int resC = n.data.compareTo(x);
			if (resC < 0) {
				return searchRec(x, n.right);
			}
			else if (resC > 0) {
				return searchRec(x, n.left);
			}
			else {
				return n;
			}
		}
	}
	
	public E minRecover() {
		E min = minRecover(); //	recupera	el	menor	del	BST
		this.root = minRemove(this.root);
		return min;
	}
	
	protected Node minRemove(Node actual) {
		if (actual.left != null) { //buscamos	el	mínimo
			actual.left = minRemove(actual.left);
		}else { //eliminamos	el	minimo
			actual = actual.right;
		}
		return actual;
	}
	
	
	public void remove(E x) throws ItemNotFound {
		this.root = removeRec(x, this.root);
	}
	
	protected Node removeRec(E x, Node actual) throws ItemNotFound {
		Node res = actual;
		if (actual == null)
			throw new ItemNotFound(x + " no esta");
		int resC = actual.data.compareTo(x);
		if (resC < 0)
			res.left = removeRec(x, actual.left);
		else if (resC > 0)
			res.right = removeRec(x, actual.right);
		else if(actual.left != null && actual.right != null) { //dos	hijos
			res.data = minRemove(actual.right).data;
			res.right = minRemove(actual.right);
		} else { //1	hijo	o	ninguno
			res = (actual.left != null) ? actual.left : actual.right;
		}return res;
	}
	
	
	@Override
	public String toString() {
		String s = "BINARY TREE\nRoot: " + this.root;
		return s;
	}
	
	public void inorden() {
        inordenRecursivo(this.root);
    }
    
    protected void inordenRecursivo(Node actual) {
        if (actual == null) {
            return;
        }
        inordenRecursivo(actual.right);  // Recorrer el subárbol derecho
        System.out.print(actual.data + " ");  // Procesar el nodo actual
        inordenRecursivo(actual.left);  // Recorrer el subárbol izquierdo
    }
	
    public int countNonLeafNodes() {
        return countNonLeafNodesRecursivo(this.root);
    }
    
    private int countNonLeafNodesRecursivo(Node nodo) {
        if (nodo == null || (nodo.left == null && nodo.right == null)) {
            return 0;
        }
        
        return 1 + countNonLeafNodesRecursivo(nodo.left) + countNonLeafNodesRecursivo(nodo.right);
    }
    
    public int getNodeHeight(E valor) {
        return getNodeHeightRecursivo(this.root, valor);
    }

    private int getNodeHeightRecursivo(Node nodo, E valor) {
        if (nodo == null) {
            return -1;  // Nodo no encontrado
        }

        if (valor.equals(nodo.data)) {
            return getHeight(nodo);  // Calcular la altura del nodo
        } else if (valor.compareTo(nodo.data) > 0) {
            return getNodeHeightRecursivo(nodo.left, valor);  // Buscar en el subárbol izquierdo
        } else {
            return getNodeHeightRecursivo(nodo.right, valor);  // Buscar en el subárbol derecho
        }
    }

    private int getHeight(Node nodo) {
        if (nodo == null) {
            return -1;
        }

        int alturaIzquierda = getHeight(nodo.left);
        int alturaDerecha = getHeight(nodo.right);

        return Math.max(alturaIzquierda, alturaDerecha) + 1;
    }
    
    public void preordenIterativo() {
        if (this.root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(this.root);

        while (!stack.isEmpty()) {
            Node nodo = stack.pop();
            System.out.print(nodo.data + " ");

            if (nodo.left != null) {
                stack.push(nodo.left);
            }

            if (nodo.right != null) {
                stack.push(nodo.right);
            }
        }
    }
    
    public int calcularArea() {
        int numNodosHojas = countLeafNodes(this.root);
        int alturaArbol = getHeight(this.root);
        return numNodosHojas * alturaArbol;
    }

    private int countLeafNodes(Node nodo) {
        if (nodo == null) {
            return 0;
        }

        if (nodo.left == null && nodo.right == null) {
            return 1;
        }

        int numNodosIzquierda = countLeafNodes(nodo.left);
        int numNodosDerecha = countLeafNodes(nodo.right);

        return numNodosIzquierda + numNodosDerecha;
    }
    
    public Node minimo() {
        return minimoRecursivo(this.root);
    }

    private Node minimoRecursivo(Node nodo) {
        if (nodo == null) {
            return null;
        }
        if (nodo.right == null) {
            return nodo;
        }
        return minimoRecursivo(nodo.right);
    }
    
    public Node maximo() {
        return maximoRecursivo(this.root);
    }

    private Node maximoRecursivo(Node nodo) {
        if (nodo == null) {
            return null;
        }
        if (nodo.left == null) {
            return nodo;
        }
        return maximoRecursivo(nodo.left);
    }
    
}
