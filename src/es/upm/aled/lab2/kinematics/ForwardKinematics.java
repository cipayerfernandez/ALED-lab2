package es.upm.aled.lab2.kinematics;

import es.upm.aled.lab2.gui.Node;

/**
 * This class implements a forward kinematics algorithm using recursion. It
 * expects a tree of Segments (defined by its length and angle with respect to
 * the previous Segment in the tree) and returns a tree of Nodes (defined by
 * their absolute coordinates in a 2-dimensional space).
 * 
 * @author rgarciacarmona
 */
public class ForwardKinematics {

	/**
	 * Returns a tree of Nodes to be used by SkeletonPanel to draw the position of
	 * an exoskeleton. This method is the public facade to a recursive method that
	 * builds the result from a tree of Segments defined by their angle and length,
	 * and the relationship between them (which Segment is children of which).
	 * 
	 * @param root    The root of the tree of Segments.
	 * @param originX The X coordinate for the origin point of the tree.
	 * @param originY The Y coordinate for the origin point of the tree.
	 * @return The tree of Nodes that represent the exoskeleton position in absolute
	 *         coordinates.
	 */
	// Public method: returns the root of the position tree
	public static Node computePositions(Segment root, double originX, double originY) {
		return computePositions(root, originX, originY, 0);
	}

	// Private helper method that implements the recursive algorithm
	// Tengo que devolver un Node, NO una lista de Nodes. Este Node deberá tener en su lista de hijos a todos los nodos 
	// cuya posición habré computado mediante cinemática directa.
	private static Node computePositions(Segment link, double baseX, double baseY, double accumulatedAngle) {
		
		// CÓDIGO GENERAL: se ejecuta siempre.
		double currentAccumulatedAngle = accumulatedAngle + link.getAngle();	// Obtengo el ángulo acumulado de cada segmento hijo.
		double coordX = baseX + link.getLength()*Math.cos(currentAccumulatedAngle);	// Obtengo la coordenada X de cada nodo hijo.
		double coordY = baseY + link.getLength()*Math.sin(currentAccumulatedAngle);	// Obtengo la coordenada Y de cada nodo hijo.
		
		// NOTA: nodeTree es el nodo padre de cada iteración. Si creo nodeTree a partir de baseX y baseY, en la última iteración
		// no se devolverá el nodo con las coordenadas actualizadas (coordX y coordY).
		Node nodeTree = new Node(coordX, coordY);	// Creo el nodo padre de cada iteración.
		
		// CASO BASE (condición de parada): que el Segment "link" no tenga hijos.
		if (link.getChildren().isEmpty()) {
			return nodeTree;
		}
		
		// PASO RECURSIVO: se ejecuta si no se cumple la condición de parada.
		for (Segment s : link.getChildren()) {	// Recorro la lista de hijos de "link".
			Node childNode = computePositions(s, coordX, coordY, currentAccumulatedAngle);	// Creo un Node hijo a partir de las coordenadas de cada hijo de "link".
			nodeTree.addChild(childNode);	// Una vez creado el node, lo añado a la lista de hijos del node padre.
		}
		return nodeTree;	// Devuelvo el árbol de nodos (último nodo padre).
	}
}












