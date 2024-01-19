import fi.uef.cs.tra.BTree;
import fi.uef.cs.tra.BTreeNode;
import fi.uef.cs.tra.LinkedQueue;

public class matalinLehtisolmu {
    public static <E> BTreeNode<E> syvinLehtisolmu(BTree<E> T) {
        BTreeNode<E> n = null;
        if (T.getRoot() == null)
            return null;

        // TODO
        LinkedQueue<BTreeNode> Q = new LinkedQueue<BTreeNode>();
        Q.offer(T.getRoot());
        while (!Q.isEmpty()) {
            n = Q.poll();

            if (n.getLeftChild() != null)
                Q.offer(n.getLeftChild());
            if (n.getRightChild() != null)
                Q.offer(n.getRightChild());
        }

           /* while (n != null) {
                Q.offer(n);
                n = n.getRightChild();
            }*/

        return n;
    } // matalinLehtisolmu()
}
