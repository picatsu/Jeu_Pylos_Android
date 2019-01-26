
package projet.pylos;


public class Recette {

    public static void main(String [] args) {
//		Etage e1 = new Etage(4,4,0);
//		Etage e2 = new Etage(3,3,1);
//		Etage e3 = new Etage(2,2,2);
//		Etage e4 = new Etage(1,1,3);
//		ArrayList<Etage> grille = new ArrayList<>();
//		grille.add(e1);
//		grille.add(e2);
//		grille.add(e3);
//		grille.add(e4);
//
        Model m = new Model(4,4,4);

        Boule b1 = new Boule("rouge", 10);
        Boule b2 = new Boule("bleu", 10);
        Boule b3 = new Boule("rouge", 10);
        Boule b4 = new Boule("bleu", 10);
        Boule b5 = new Boule("rouge", 10);
//
        Boule b6 = new Boule("rouge", 10);
        Boule b7 = new Boule("bleu", 10);
//		Boule b8 = new Boule("rouge", 10);
//		Boule b9 = new Boule("bleu", 10);
        Boule b10 = new Boule("rouge", 10);

        //done
        //upper right side
//		m.put(b1, new Coordonne(0,3,0));
//		m.put(b2, new Coordonne(1,3,0));
//		m.put(b3, new Coordonne(0,2,0));
//		m.put(b4, new Coordonne(1,2,0));
//		System.out.println(m);
//		System.out.println(m.canPut(b5, new Coordonne(0,2,1)));

        //done
//		lower right side
        m.put(b1, new Coordonne(3,3,0));
        m.put(b2, new Coordonne(3,2,0));
        m.put(b3, new Coordonne(2,2,0));
        m.put(b4, new Coordonne(2,3,0));
        m.put(b6, new Coordonne(3, 3, 0));
        System.out.println(m);
        System.out.println(m.canPut(b5, new Coordonne(2,2,1)));

        //done
        //upper left side
//		m.put(b1, new Coordonne(0,0,0));
//		m.put(b2, new Coordonne(0,1,0));
//		m.put(b3, new Coordonne(1,0,0));
//		m.put(b4, new Coordonne(1,1,0));
//
//		System.out.println(m);
//		System.out.println(m.canPut(b6, new Coordonne(0,0,1)));

        //done
        //lower left side
//		m.put(b1, new Coordonne(3,0,0));
//		m.put(b1, new Coordonne(3,1,0));
//		m.put(b1, new Coordonne(2,0,0));
//		m.put(b1, new Coordonne(2,1,0));
//		System.out.println(m);
//		System.out.println(m.canPut(b1, new Coordonne(2,0,1)));

        //done
        //middle side
//		m.put(b1, new Coordonne(1,1,0));
//		m.put(b1, new Coordonne(2,1,0));
//		m.put(b1, new Coordonne(1,2,0));
//		m.put(b1, new Coordonne(2,2,0));
//		System.out.println(m);
//		System.out.println(m.canPut(b1, new Coordonne(1,1,1)));

        //done
//		middle right side
//		m.put(b1, new Coordonne(1,3,0));
//		m.put(b1, new Coordonne(2,3,0));
//		m.put(b1, new Coordonne(1,2,0));
//		m.put(b1, new Coordonne(2,2,0));
//		System.out.println(m);
//		System.out.println(m.canPut(b1, new Coordonne(2,1,1)));

        //done
        //middle left side
//		m.put(b1, new Coordonne(1,0,0));
//		m.put(b1, new Coordonne(1,1,0));
//		m.put(b1, new Coordonne(2,0,0));
//		m.put(b1, new Coordonne(2,1,0));
//		System.out.println(m);
//		System.out.println(m.canPut(b1, new Coordonne(1,0,1)));

        //done
        //lower middle side
//		m.put(b1, new Coordonne(2,1,0));
//		m.put(b1, new Coordonne(2,2,0));
//		m.put(b1, new Coordonne(3,1,0));
//		m.put(b1, new Coordonne(3,2,0));
//		System.out.println(m);
//		System.out.println(m.canPut(b1, new Coordonne(2,1,1)));

        //done
        //upper mid side
//		m.put(b1, new Coordonne(0,1,0));
//		m.put(b1, new Coordonne(1,1,0));
//		m.put(b1, new Coordonne(0,2,0));
//		m.put(b1, new Coordonne(1,2,0));
//		System.out.println(m);
//		System.out.println(m.canPut(b1, new Coordonne(0,1,1)));

//		m.put(b1, new Coordonne(0,0,0));
//		m.put(b1, new Coordonne(0,1,0));
//		m.put(b1, new Coordonne(1,0,0));
//		m.put(b1, new Coordonne(1,1,0));
//		m.put(b1, new Coordonne(0,0,1));
//		m.put(b1, new Coordonne(0,2,0));
//		m.put(b1, new Coordonne(0,2,0));
//		m.put(b1, new Coordonne(1,2,0));
//		m.put(b1, new Coordonne(0,1,1));
//		m.put(b1, new Coordonne(0,3,0));
//		m.put(b1, new Coordonne(1,3,0));
//		m.put(b1, new Coordonne(0,2,1));
//		m.put(b1, new Coordonne(2,0,0));
//		m.put(b1, new Coordonne(2,1,0));
//		m.put(b1, new Coordonne(1,0,1));
//		m.put(b1, new Coordonne(3,0,0));
//		m.put(b1, new Coordonne(3,1,0));
//		m.put(b1, new Coordonne(2,0,1));
//
//		m.put(b1, new Coordonne(2,2,0));
//		m.put(b1, new Coordonne(3,2,0));
//		m.put(b1, new Coordonne(2,1,1));
//		m.put(b1, new Coordonne(1,1,1));
//
//		m.put(b1, new Coordonne(2,3,0));
//		m.put(b1, new Coordonne(3,3,0));
//		m.put(b1, new Coordonne(1,2,1));
//		m.put(b1, new Coordonne(2,2,1));
//
//		System.out.println(m);
//		System.out.println(m.canPut(b1, new Coordonne(0,0,2)));
//		m.put(b1, new Coordonne(0,2,0));
//		m.put(b1, new Coordonne(1,2,0));

//		System.out.println(m.canPut(b1, new Coordonne(0,0,0)));
//		m.put(b1, new Coordonne(0,0,0));
//		System.out.println(m.canPut(b1, new Coordonne(0,0,0)));
//		m.remove(b1);
//		System.out.println(m.canPut(b1, new Coordonne(0,0,0)));


        //test de remove extensif
//		m.put(b1, new Coordonne(0,0,0));
//		m.put(b2, new Coordonne(0,1,0));
//		m.put(b3, new Coordonne(1,0,0));
//		m.put(b4, new Coordonne(1,1,0));
//		m.put(b5, new Coordonne(0,0,1));
//		//true car b1 est pilie de b5
//		System.out.println(b1.isPillar());
//		m.put(b6, new Coordonne(2,0,0));
//		m.put(b7, new Coordonne(2,1,0));
//		m.put(b10, new Coordonne(1,0,1));
//		//true car b9 est pilie de b10
//		System.out.println(b6.isPillar());
//		m.remove(b5);
//		//b5 retiree donc b1 nest plus un pillier, donc false
//		//par contre b3 est toujours un pillier pour b10, donc true
//		System.out.println(b1.isPillar());
//		System.out.println(b3.isPillar());
//		m.remove(b1);
//		m.remove(b3);
//		m.remove(b8);
//		m.remove(b10);
//		System.out.println(b1.isPillar());
//		System.out.println(b2.isPillar());
//		System.out.println(b3.isPillar());
//		System.out.println(m);

//		Boule b1 = new Boule("rouge", 10);
//		Boule b2 = new Boule("rouge", 10);
//		Boule b3 = new Boule("rouge", 10);
//		Boule b4 = new Boule("rouge", 10);
//		Boule r1 = new Boule("rouge", 10);
//		Boule r2 = new Boule("rouge", 10);
//		Boule r3 = new Boule("rouge", 10);
//
//		m.put(b1, new Coordonne(0,0,0));
//		m.put(b2, new Coordonne(1,0,0));
//		m.put(b3, new Coordonne(0,1,0));
//		m.put(b4, new Coordonne(1,1,0));
//		m.put(r1, new Coordonne(0,0,1));
//		System.out.println(m.oneSquareColorPillar(r1));

//		System.out.println(m.canPut(b1, new Coordonne(0,2,1)));
//		System.out.println(m);

//		m.put(b1, new Coordonne(0,0,0));
//		m.put(b2, new Coordonne(0,1,0));
//		m.put(b3, new Coordonne(1,0,0));
//		m.put(b4, new Coordonne(1,1,0));
//		m.put(b5, new Coordonne(0,0,1));
//		System.out.println(m.canBeDeplacedTo(b5, new Coordonne(0, 0, 2)));
//		m.remove(b5);
//		m.put(b5, new Coordonne(0,0,1));
//		System.out.println(m);

        //test pilliers
//		m.put(b1, new Coordonne(0,0,0));
//		m.put(b2, new Coordonne(0,1,0));
//		m.put(b3, new Coordonne(1,0,0));
//		m.put(b4, new Coordonne(1,1,0));
//		m.put(b5, new Coordonne(0,0,1));
//		System.out.println(b1.isPillar());
//		System.out.println(b2.isPillar());
//		System.out.println(b3.isPillar());
//		System.out.println(b4.isPillar());
//		System.out.println(b5.isHasPillar());
//		System.out.println(m);

        //test placing the same ball
//		m.put(b1, new Coordonne(0,0,0));
//		System.out.println(m);
//		m.remove(b1);
//		System.out.println(m);
//		m.put(b1, new Coordonne(1,0,0));
//		System.out.println(m);
    }

}

