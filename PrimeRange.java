import java.io.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

class PrimeRange    {
    public static List<Integer> SieveOfEratosthenesCOWAL(int minrange, int maxrange)  {
        List<Integer> arr = new CopyOnWriteArrayList<Integer>();
        for(int i = 2; i <= maxrange; i++)
            arr.add(i);
        
        ListIterator<Integer> lIterate = arr.listIterator();

        while(lIterate.hasNext())   {
            Integer p = lIterate.next();
            for(Integer i = p * p; i < maxrange; i += p)    {
                arr.remove(i);
            }
        }

        return arr;
    }
    
    public static List<Integer> SieveOfEratosthenesLHM(int minrange, int maxrange){
        LinkedHashMap<Integer, Boolean> primes = new LinkedHashMap<Integer, Boolean>();
        for(int i = 2; i <= maxrange; i++)
            primes.put(i, true);

        Iterator<Map.Entry<Integer, Boolean>> primeIterator = primes.entrySet().iterator();

        while(primeIterator.hasNext())  {
            Map.Entry<Integer, Boolean> me = (Map.Entry<Integer, Boolean>)primeIterator.next();
            if(me.getValue() == false)
                    continue;
            for(Integer i = me.getKey() * me.getKey(); i <= maxrange; i += me.getKey()) {
                primes.replace(i, false);
            }
        }

        List<Integer> arr = new ArrayList<Integer>();

        primeIterator = primes.entrySet().iterator();

        while(primeIterator.hasNext())  {
            Map.Entry<Integer, Boolean> me = (Map.Entry<Integer, Boolean>)primeIterator.next();
            if(me.getValue() == true)
                arr.add(me.getKey());
                
        return arr;
        }

    }

    public static void main(String args[])throws IOException    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Range - ");
        String range = br.readLine();
        String ranges[] = range.split(" ");
        int minrange = Integer.parseInt(ranges[0]);
        int maxrange = Integer.parseInt(ranges[1]);
        List<Integer> SoECOWAL = SieveOfEratosthenesCOWAL(minrange, maxrange);
        Integer[] arr = SoECOWAL.toArray(new Integer[SoECOWAL.size()]);
        for(Integer a : arr)    
            if(a > minrange)    
                System.out.print(a+" ");
        System.out.println();

        List<Integer> SoELHM = SieveOfEratosthenesLHM(minrange, maxrange);
        arr = SoELHM.toArray(new Integer[SoELHM.size()]);
        for(Integer a : arr)    
            if(a > minrange)    
                System.out.print(a+" ");
        System.out.println();
    }
}