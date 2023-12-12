package Arbol;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Tree {
    private TreeNode root;

    public Tree(TreeNode root) {
        this.root = root;
    }

    public void printTree(TreeNode node, String indent) {
        System.out.println(indent + "└─ " + node.getName());

        for (int i = 0; i < node.getChildren().size(); i++) {
            printTree(node.getChildren().get(i), indent + "   ");
        }
    }

    public TreeNode findNode(String nodeName, TreeNode node) {
        if (node == null) {
            node = root;
        }

        if (node.getName().equals(nodeName)) {
            return node;
        }

        for (TreeNode child : node.getChildren()) {
            TreeNode foundNode = findNode(nodeName, child);
            if (foundNode != null) {
                return foundNode;
            }
        }

        return null;
    }

    public void addNode(String parentNodeName, String newNodeName) {
        TreeNode parentNode = findNode(parentNodeName, null);
        if (parentNode != null) {
            parentNode.getChildren().add(new TreeNode(newNodeName));
        } else {
            System.out.println("No se encontró el nodo padre '" + parentNodeName + "'.");
        }
    }

    public void editNode(String nodeName, String newName) {
        TreeNode nodeToEdit = findNode(nodeName, null);
        if (nodeToEdit != null) {
            nodeToEdit.setName(newName);
        } else {
            System.out.println("No se encontró el nodo '" + nodeName + "'.");
        }
    }

    public void deleteNode(String nodeName) {
        TreeNode nodeToDelete = findNode(nodeName, null);
        if (nodeToDelete != null) {
            if (nodeToDelete == root) {
                // No se puede eliminar el nodo raíz
                System.out.println("No se puede eliminar el nodo raíz.");
            } else {
                TreeNode parent = findParentNode(nodeName, null);
                if (parent != null) {
                    if (!nodeToDelete.getChildren().isEmpty()) {
                        // Convertir el primer hijo en el nuevo padre
                        TreeNode firstChild = nodeToDelete.getChildren().get(0);
                        firstChild.getChildren().addAll(nodeToDelete.getChildren().subList(1, nodeToDelete.getChildren().size()));
                        parent.getChildren().add(parent.getChildren().indexOf(nodeToDelete), firstChild);
                    }
                    parent.getChildren().remove(nodeToDelete);
                    System.out.println("El nodo '" + nodeName + "' se eliminó, y el primer hijo se convirtió en el nuevo padre sin cambiar la posición de la rama.");
                }
            }
        } else {
            System.out.println("No se encontró el nodo '" + nodeName + "'.");
        }
    }

    private TreeNode findParentNode(String nodeName, TreeNode node) {
        if (node == null) {
            node = root;
        }

        for (TreeNode child : node.getChildren()) {
            if (child.getName().equals(nodeName)) {
                return node;
            }

            TreeNode parent = findParentNode(nodeName, child);
            if (parent != null) {
                return parent;
            }
        }

        return null;
    }

    // Guardar la estructura del árbol en un archivo JSON
    public void saveTreeToFile(String filePath) throws IOException {
       // ObjectMapper mapper = new ObjectMapper();
      //  mapper.writeValue(new File(filePath), this);
        System.out.println("Estructura del árbol guardada en el archivo.");
    //}

    // Cargar la estructura del árbol desde un archivo JSON
  //  public static Tree loadTreeFromFile(String filePath) throws IOException {
        //ObjectMapper mapper = new ObjectMapper();
        if (new File(filePath).exists()) {
        //    Tree tree = mapper.readValue(new File(filePath), Tree.class);
            System.out.println("Estructura del árbol cargada desde el archivo.");
          //  return tree;
        } else {
            System.out.println("El archivo no existe. Se creará un nuevo árbol.");
           // return null;
        }
    }
}

class TreeNode {
    private String name;
    private List<TreeNode> children = new ArrayList<>();

    public TreeNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TreeNode> getChildren() {
        return children;
    }
}
