/**
 * Implements a red-black tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss 
 * @author (modified By) Robson Adem (modifications) keep size of each node and update after rotations, select kth smallest element
 */
public class RedBlackTree<AnyType extends Comparable<? super AnyType>>
{

    private RedBlackNode<AnyType> header;
    private RedBlackNode<AnyType> nullNode;

    private static final int BLACK = 1;    // BLACK must be 1
    private static final int RED   = 0;

    // Used in insert routine and its helpers
    private RedBlackNode<AnyType> current;
    private RedBlackNode<AnyType> parent;
    private RedBlackNode<AnyType> grand;
    private RedBlackNode<AnyType> great;
    private static class RedBlackNode<AnyType>
    {
        AnyType               element;    // The data in the node
        RedBlackNode<AnyType> left;       // Left child
        RedBlackNode<AnyType> right;      // Right child
        int                   color;      // Color
        int                   size;        //size 
        // Constructors
        RedBlackNode( AnyType theElement )
        {
            this( theElement, null, null );

        }

        RedBlackNode( AnyType theElement, RedBlackNode<AnyType> lt, RedBlackNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
            color    = RedBlackTree.BLACK;
            size=0;
        }
    }

    /**
     * Construct the tree.
     */
    public RedBlackTree( )
    {
        nullNode = new RedBlackNode<AnyType>( null );
        nullNode.left = nullNode.right = nullNode;
        header      = new RedBlackNode<AnyType>( null );
        header.left = header.right = nullNode;
    }

    /**
     * Compare item and t.element, using compareTo, with
     * caveat that if t is header, then item is always larger.
     * This routine is called if is possible that t is header.
     * If it is not possible for t to be header, use compareTo directly.
     */
    private final int compare( AnyType item, RedBlackNode<AnyType> t )
    {
        if( t == header )
            return 1;
        else
            return item.compareTo( t.element );    
    }

    /**
     * Insert into the tree.
     * @param item the item to insert.
     * @throws DuplicateItemException if item is already present.
     */
    public void insert( AnyType item ) throws DuplicateException
    {
        current = parent = grand = header;
        nullNode.element = item;

        while( compare( item, current ) != 0 )
        {
            great = grand; grand = parent; parent = current;
            current = compare( item, current ) < 0 ? current.left : current.right;
            // Check if two red children; fix if so
            if( current.left.color == RED && current.right.color == RED )
                handleReorient( item );
        }
        // Insertion fails if already present
        if( current != nullNode )            throw new DuplicateException();
        current = new RedBlackNode<AnyType>( item, nullNode, nullNode );
        // Attach to parent
        if( compare( item, parent ) < 0 )     parent.left = current;
        else                                    parent.right = current;
        find(item);//find the item percolating down and increase the size of each node visited
        handleReorient( item );
    }

    /**
     * Find the kth smallest item in the tree.
     * @param k the desired rank (1 is the smallest item).
     * @return the kth smallest item in the tree.
     * @throws IllegalArgumentException if k is less
     *     than 1 or more than the size of the subtree.
     */
    public AnyType select( int k )
    {
        if(k<=0||k>header.right.size||header.right==nullNode){
            throw new IllegalArgumentException( );
        }

        return findKth( k, header.right );
    }

    /**
     * Internal method to find kth smallest item in a subtree.
     * @param k the desired rank (1 is the smallest item).
     * @return the node containing the kth smallest item in the subtree.
     * @throws IllegalArgumentException if k is less
     *     than 1 or more than the size of the subtree.
     */
    protected AnyType findKth( int k, RedBlackNode<AnyType> t )
    {
        if( t == null){
            throw new IllegalArgumentException( );
        }
        int leftSize = ( t.left != nullNode) ? ((RedBlackNode<AnyType>) t.left).size : 0;

        if( k <= leftSize )
            return findKth( k, t.left );
        else if( k == leftSize + 1 )
            return t.element;
        else{
            return findKth( k - leftSize - 1, t.right );
        }
    }

    /**
     * Remove from the tree.
     * @param x the item to remove.
     * @throws UnsupportedOperationException if called.
     */
    public void remove( AnyType x )
    {
        throw new UnsupportedOperationException( );
    }

    /**
     * Find the smallest item  the tree.
     * @return the smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            return null;

        RedBlackNode<AnyType> itr = header.right;

        while( itr.left != nullNode )
            itr = itr.left;

        return itr.element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item or null if empty.
     */
    public AnyType findMax( )
    {
        if( isEmpty( ) )
            return null;

        RedBlackNode<AnyType> itr = header.right;

        while( itr.right != nullNode )
            itr = itr.right;

        return itr.element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return the matching item or null if not found.
     */
    public AnyType find( AnyType x )
    {
        nullNode.element = x;
        current = header.right;

        for( ; ; )
        {
            if( x.compareTo( current.element ) < 0 ){
                //Increasing the size apercolating down
                current.size++;
                current = current.left;
            }
            else if( x.compareTo( current.element ) > 0 ) {
                //Increasing the size percolating down
                current.size++;
                current = current.right;
            }
            else if( current != nullNode ){
                //Increasing the size percolating down
                current.size++;
                return current.element;
            }
            else
                return null;
        }
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        header.right = nullNode;
    }

    /**
     * Print all items.
     */
    public void printTree( )
    {
        printTree( header.right );
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the tree.
     */
    private void printTree( RedBlackNode<AnyType> t )
    {
        if( t != nullNode )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return header.right == nullNode;
    }

    /**
     * Internal routine that is called during an insertion
     * if a node has two red children. Performs flip and rotations.
     * @param item the item being inserted.
     */
    private void handleReorient( AnyType item )
    {
        // Do the color flip
        current.color = RED;
        current.left.color = BLACK;
        current.right.color = BLACK;

        if( parent.color == RED )   // Have to rotate
        {
            grand.color = RED;
            if( ( compare( item, grand ) < 0 ) !=
            ( compare( item, parent ) < 0 ) )
                parent = rotate( item, grand );  // Start dbl rotate
            current = rotate( item, great );
            current.color = BLACK;
        }
        header.right.color = BLACK; // Make root black
    }

    /**
     * Internal routine that performs a single or double rotation.
     * Because the result is attached to the parent, there are four cases.
     * Called by handleReorient.
     * @param item the item in handleReorient.
     * @param parent the parent of the root of the rotated subtree.
     * @return the root of the rotated subtree.
     */
    private RedBlackNode<AnyType> rotate( AnyType item, RedBlackNode<AnyType> parent )
    {
        if( compare( item, parent ) < 0 )
            return parent.left = compare( item, parent.left ) < 0 ?
                rotateWithLeftChild( parent.left )  :  // LL
            rotateWithRightChild( parent.left ) ;  // LR
        else
            return parent.right = compare( item, parent.right ) < 0 ?
                rotateWithLeftChild( parent.right ) :  // RL
            rotateWithRightChild( parent.right );  // RR
    }

    /**
     * Rotate binary tree node with left child.
     */
    private static <AnyType> RedBlackNode<AnyType> rotateWithLeftChild( RedBlackNode<AnyType> k2 )
    {
       RedBlackNode<AnyType> k1 = k2.left;
        k2.left = k1.right;
        // ***********updating size parameters after rotation**********
        k2.size=k2.size-k1.size+k1.right.size;
        k1.right = k2;
        // ***********updating size parameters after rotation**********
        k1.size=k1.right.size+k1.left.size+1;
        return k1;
    }

    /**
     * Rotate binary tree node with right child.
     */
    private static <AnyType> RedBlackNode<AnyType> rotateWithRightChild( RedBlackNode<AnyType> k1 )
    {
        RedBlackNode<AnyType> k2 = k1.right;
        k1.right = k2.left;
         //************updating size parameter after rotation************
        k1.size=k1.size-k2.size+k2.left.size;
        k2.left = k1;
         //************updating size parameter after rotation************
        k2.size=k2.left.size+k2.right.size+1;
        return k2;
    }
}

