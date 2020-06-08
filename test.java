import hashTable.*;
import searchtrees.*;
import java.util.*;

public class test {
	int n;                   // To store the number of Strings to be entered
	int select;              // To know either userdefined,predefined in hashing or order,random in trees
	int s;                   // To know either HashTable or Trees Operations to be performed
	int s1;                  // To know the technique i.e either Hashing Techniques or Trees Types
	int s2;                  // To know either insert,search,delete or back in trees
    CuckooHashTable<String> hash1 = new CuckooHashTable<>( new StringHashFamily( 3 ), 2000);
    QuadraticProbingHashTable<String> hash2 = new QuadraticProbingHashTable<>( );
    SeparateChainingHashTable<String> hash3 = new SeparateChainingHashTable<>( );
    
    BinarySearchTree<Integer> bst = new BinarySearchTree<>( );
	AVLTree<Integer> avl = new AVLTree<>( );
	RedBlackBST<Integer, Integer> rbt = new RedBlackBST<Integer, Integer>();
	SplayTree<Integer> st = new SplayTree<Integer>( );

	public static void main(String[] args) {
		test t = new test();                     // Creating an object for test class to access global variables
		test t1 = new test();
		Scanner input = new Scanner(System.in);
		while(true)
		{
			System.out.println("1.HashTable 2.Trees 3.Exit");
			System.out.printf("Select Option : ");
			t.s=input.nextInt();
			switch(t.s)                             // Switch Case to select HashTable or Trees
			{
				case 1 :                                              //HashTable
				{
					System.out.println("1.Userdefined 2.Predefined 3.Back");
					System.out.printf("Select Option : ");
					t.select=input.nextInt();
					switch(t.select)
					{
						case 1 :                   //UserDefined
						{
							System.out.printf("   Enter number of Strings to be stored : ");
							t.n=input.nextInt();
							while(true)
							{
								System.out.println("      1.CuckooHashing  2.QuadraticProbing 3.SeperateChaining 4.Back");
								System.out.printf("      Select Hashing Technique : ");
								t.s1=input.nextInt();
								if(t.s1==4)                                // if input is 4 then breaks to go back
									break;
								t.randomStringgenerator(t.n,t.s1);
		
								System.out.printf("   Enter number of Strings to search : ");
								t1.n=input.nextInt();
								t1.randomStringgenerator2(t1.n,t.s1);
							}              // Close of inner While loop
							break;
						}
						case 2 :                  //Predefined
						{
							while(true)
							{
								System.out.println("      1.CuckooHashing  2.QuadraticProbing 3.SeperateChaining 4.Back");
								System.out.printf("      Select Hashing Technique : ");
								t.s1=input.nextInt();
								if(t.s1==4)
									break;
								t.randomStringgenerator3(t.s1);
							}              // Close of inner While loop
							break;
						}
						case 3 :                 // Back
						{
							break;
						}
					}
					break;
				}
				case 2 :                                           // Trees
				{
					while(true)
					{
					System.out.println("   1.BinarySearchTree 2.AVLTree 3.RedBlackBST 4.SplayTree 5.Back");
					System.out.printf("   Select Option : ");
					t.s1=input.nextInt();
					if(t.s1==5)
						break;
					System.out.println("      1.Order 2.Random");
					System.out.printf("      Select Option : ");
					t.select=input.nextInt();
					boolean d = true;
					while(d)
					{
						System.out.println("         1.Insert 2.Search 3.Delete 4.Back");
						System.out.printf("         Select Option : ");
						t.s2=input.nextInt();
						switch(t.s2)
						{
							case 1 :                                   // Insertion
							{
								t.insertkey(t.s1,t.select);
								break;
							}
							case 2 :                                   // Search
							{
								t.searchkey(t.s1);
								break;
							}
							case 3 :                                   // Delete
							{
								t.deletekey(t.s1,t.select);
								break;
							}
							case 4 :
							{
								d=false;
							}
						}                                              // Close of Switch Case
					}                                                  // Close of 2nd inner while loop
					}                                                  // Close of 1st inner while loop
					break;
				}                                                      // Close of Case 2
			
				case 3 :                                               // exit
				{
					System.exit(0);
				}
				
			} // Close of Switch Case
			
		}     // Close of outer While Loop
		
	}         // Close of Main Method

// Method for first Question to insert a random string in the hashtable
	public void randomStringgenerator(long l, int m) {
		  
	    long sumTime=0,avgTime=0;        // Variables to calculate avg time
	    int size=10;                     //  Default size of the String
	    String generatedString;          // A string to store the random string generated

	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder((int)l);

	    
	    for(int j=0 ; j<l ; j++)                    // Loop to generate l no. of strings
	    {
	    	
	    	for (int i = 0 ; i < size ; i++)        // Loop to generate string of size=10
	    	{
	    			int a = (int)(random.nextFloat() * 10);
	    			buffer.append(a);
	    	}
	    	generatedString=buffer.toString();
	    	long startTime = System.nanoTime();
	    	switch(m)                             // Switch Case to select Hashing Technique
	    	{
	    		case 1 :
	    			hash1.insert(generatedString);    // Adds String via Cuckoo Hashing
	    			break;
	    		case 2 :
	    			hash2.insert(generatedString);    // Adds String via Quadratic Probing
	    			break;
	    		case 3 :
	    			hash3.insert(generatedString);    // Adds String via Separate Chaining
	    			break;
	    		case 4 :                              // Back
	    			break;
	    		default :
	    			System.out.println("      Invalid Input\n");
	    			break;
	    	}                                    // Close of switch case
	    	long endTime = System.nanoTime();
	    	// if loop to break outer for loop if input other than 1,2,3
	    	if(m!=1&&m!=2&&m!=3)                   // to get out of loop
	    		break;    
	    	buffer = new StringBuilder((int)l);
	  
	    	sumTime = sumTime + (endTime-startTime);
	    }                                       // Close of outer for loop
	    avgTime = sumTime/l;
	    		
	    System.out.println("      Avg Time for "+l+" Strings insertion : "+avgTime+" nano Seconds\n");

	}                                            // Close of 1st Method
	
// Method for Second Question to search the random string generated in the hash table
	public void randomStringgenerator2(long l,int m) {
		int size=10;                           // Default Size of the string to be generated
		long sumTime=0,avgTime=0;              // Variables to calculate avg time
		String generatedString1;

		Random random = new Random();
		StringBuilder b1 = new StringBuilder((int)l);
		for(int j=0 ; j<l ; j++)
		{
			
			for(int i=0 ; i<size ; i++)
			{
				int a = (int)(random.nextFloat()*10);
				b1.append(a);
			}
			generatedString1=b1.toString();
			long startTime = System.nanoTime();
			switch(m)
			{
				case 1 :                           // Search via Cuckoo Hashing
				{
					if(hash1.contains(generatedString1))
					{
					//System.out.println("      "+generatedString1+" Already Exists in the Hash Table");
					System.out.println("      Removing "+generatedString1+" from the Hash Table\n");
					hash1.remove(generatedString1);
					}
					break;
				}
				case 2 :                          // Search via Quadratic Probing
				{
					if(hash2.contains(generatedString1))
					{
					//System.out.println("      "+generatedString1+" Already Exists in the Hash Table");
					System.out.println("      Removing "+generatedString1+" from the Hash Table\n");
					hash2.remove(generatedString1);
					}
					break;
				}
				case 3 :                         // Search via Separate Chaining
				{
					if(hash3.contains(generatedString1))
					{
					//System.out.println("      "+generatedString1+" Already Exists in the Hash Table");
					System.out.println("      Removing "+generatedString1+" from the Hash Table\n");
					hash3.remove(generatedString1);
					}
					break;
				}
				case 4 :                        // Back
					break;
				default :
		    		System.out.println("      Invalid Input\n");
		    		break;
			}            // Close of Switch Case
			long endTime = System.nanoTime();
			b1= new StringBuilder((int)l);
			
	    	sumTime = sumTime + (endTime-startTime);	
		}                                      // Close of outer for loop
		avgTime = sumTime/l;
		//Clear the hash table to avoid interruption of old strings
		hash1.makeEmpty(); 
		hash2.makeEmpty();
		hash3.makeEmpty();
		System.out.println("      Hash Table Searched");
		System.out.println("      Avg Time for "+l+" Strings search : "+avgTime+" nano Seconds\n");
		
	}                                          // Close of 2nd Method
	
// Method for Third Question to generate predefined number of Strings
	public void randomStringgenerator3(int m) {
		long n;
		test t = new test();
		for(int i=1;i<=20;i++)
		{
			n=(long)Math.pow(2,i);
			t.randomStringgenerator(n,m);
		}
		System.out.println("   Searching Starts");
		for(int i=1;i<=20;i++)
		{
			n=(long)Math.pow(2,i);
			t.randomStringgenerator2(n, m);
		}
		
	}                                        // Close of 3rd Method
	
// Method for Question 4(a) and 5(a) to insert an element in the Tree
	public void insertkey(int m,int z)
	{
		int max=100000,min=1,x;
		long sumTime=0,avgTime=0;

		for(int i=1;i<=100000;i++)
		{
			x=(int)((Math.random()*((max-min)+1))+min);
			long startTime = System.nanoTime();
			switch(m)
			{
				case 1 :                        // BinarySearchTree
				{
					if(z==1)                                   // Order numbers
						bst.insert(i);
					else if(z==2)                              // Random numbers
						bst.insert(x);
					break;
				}
				case 2 :                       // AVL Tree
				{
					if(z==1)                                   // Order numbers
						avl.insert(i);
					else if(z==2)                              // Random numbers
						avl.insert(x);
					break;
				}
				case 3 :                       // RedBlackBST
				{
					if(z==1)                                   // Order numbers
						rbt.put(i,i);
					else if(z==2)                              // Random numbers
						rbt.put(i,x);
					break;
				}
				case 4 :                       // Splay Tree
				{
					if(z==1)                                   // Order numbers
						st.insert(i);
					else if(z==2)                              // Random numbers
						st.insert(x);
					break;
				}
				case 5 :
				{
					break;
				}

			}                                  // Close of Switch Case
			
			long endTime = System.nanoTime();
			sumTime = sumTime + (endTime - startTime);
			
		}                                      // Close of for loop
		
		avgTime = sumTime/(100000);
		System.out.println("         Avg time for each insertion : "+avgTime+" nano Seconds\n");
		
	}                                         // Close of insertkey() method

// Method for Question 4(b) and 5(b) to search an element in the Tree
	public void searchkey(int m)
	{
		int max=100000,min=1,x;
		long sumTime=0,avgTime=0;

		for(int i=1;i<=100000;i++)
		{
			x=(int)((Math.random()*((max-min)+1))+min);
			long startTime = System.nanoTime();
			switch(m)
			{
				case 1 :                        // BinarySearchTree
				{
					bst.contains(x);
						
					break;
				}
				case 2 :                       // AVL Tree
				{
					avl.contains(x);
					break;
				}
				case 3 :                       // RedBlackBST
				{
					rbt.get(x);
					break;
				}
				case 4 :                       // Splay Tree
				{
					st.contains(x);
					break;
				}
				case 5 :
					break;
			}                                  // Close of Switch Case
			
			long endTime = System.nanoTime();
			sumTime = sumTime + (endTime - startTime);
			
		}                                     // Close of for loop
		
		avgTime = sumTime/(100000);
		System.out.println("         Avg time for each search : "+avgTime+" nano Seconds\n");
		
	}                                         // Close of searchkey() method
	
// Method for Question 4(c) and 5(c) to delete an element in the Tree
	public void deletekey(int m, int z)
	{
		int max=100000,min=1,x;
		long sumTime=0,avgTime=0;
	
		for(int i=100000;i>=1;i--)
		{
			x=(int)((Math.random()*((max-min)+1))+min);
			long startTime = System.nanoTime();
			switch(m)
			{
				case 1 :                        // BinarySearchTree
				{
					if(z==1)
						bst.remove(i);
					else if(z==2)
						bst.remove(x);
					break;
				}
				case 2 :                       // AVL Tree
				{
					if(z==1)
					{
					avl.remove(i);
					//avl.checkBalance();
					}
					else if(z==2)
					{
						avl.remove(x);
						//avl.checkBalance();
					}
					break;
				}
				case 3 :                       // RedBlackBST
				{
					if(z==1)
						rbt.delete(i);
					else if(z==2)
						rbt.delete(x);
					break;
				}
				case 4 :                       // Splay Tree
				{
					if(z==1)
						st.remove(i);
					else if(z==2)
						st.remove(x);
					break;
				}
				case 5 :
					break;
			}                                  // Close of Switch Case
			
			long endTime = System.nanoTime();
			sumTime = sumTime + (endTime - startTime);
			
		}                                     // Close of for loop
		
		avgTime = sumTime/(100000);
		System.out.println("         Avg time for each deletion : "+avgTime+" nano Seconds\n");
		
	}                                         // Close of deletekey() method
	
}                                             // Close of the test class

