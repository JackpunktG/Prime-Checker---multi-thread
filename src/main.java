

public class main
{
    static boolean isprime(long prime) {                //main method for prime checking if entered number is prime in range mode

        if (prime == 1) return true;
        if (prime == 2) return true;

        if (prime % 2 == 0) return false;
        long halfprime = prime / 2;

        for (long i = 3; i <= halfprime; i += 2) {
            if (prime % i == 0) return false;

        }
        return true;
    }

    static boolean isprimeSingle(long prime, long amount) {   //Single seeker mode, to divide a number up into test ranges dynamically based on number of threads

        if (prime == 1) return true;
        if (prime == 2) return true;

        if (prime % 2 == 0) return false;

        if (prime == amount) {      //incase just one thread
            for (long i = 3; i < amount; i += 2) {
                if (prime % i == 0) return false;

            }
        } else {
            for (long i = 3; i <= amount; i += 2) {
                if (prime % i == 0) return false;

            }
        }
        return true;
    }

    public static void main (String[] args)
    {
        int options = 1; //1 RANGE MODE - 2 SINGLE SEARCH

        switch (options) {
            case 1: {  //Range MODE

                prime.option = 1;

                long primeRangeLower = 0;
                long primeRangeUpper = 1000;



                int threadMAX = 8;              // 1 Thread will just be main
                int threadNumber;


                for (threadNumber = 1; threadNumber <= threadMAX; threadNumber++) {
                    long primeLower = primeRangeLower;
                    long primeUpper = primeRangeUpper;


                    long startTime = System.nanoTime();             //Starting timer
                    int addThreads = threadNumber - 1;              // -1 because main is already thread
                    prime[] thread = new prime[addThreads];         //thread classes


                    if (threadNumber > 1) {
                        long amount = (primeUpper - primeLower)/ threadNumber;          // To split the range dynamically depending on how many threads
                        long numbersplit = amount + primeLower + 1;                     //starting the range 1 over the value of the last

                        for (int i = 0; i < addThreads; i++) {

                            if (i == (addThreads - 1)) {
                                thread[i] = new prime(numbersplit, primeUpper);             //Last thread getting all the last values (in case of rounding error)
                            } else {
                                thread[i] = new prime(numbersplit, numbersplit + amount);       //adding value range to classes
                                numbersplit = numbersplit + amount + 1;
                            }
                        }
                        primeUpper = primeLower + amount;       //Setting the prime upper back for the main thread
                    }


                    if (threadNumber > 1) {             //Starting the threads
                        for (int i = 0; i < addThreads; i++) {
                            thread[i].t.start();
                        }
                    }


                    for (primeLower = primeLower; primeLower <= primeUpper; primeLower++) {         //Main thread test
                        if (isprime(primeLower) == true) System.out.println(primeLower + " is PRIME!!" + " by thread: " + Thread.currentThread().getName());

                    }


                    if (threadNumber > 1) {                     //Waiting for the other threads to finish
                        try {
                            for (int i = 0; i < addThreads; i++) {
                                thread[i].t.join();
                            }
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    long endTime = System.nanoTime();           //ending time and time calculation

                    long durationInMillis = (endTime - startTime) / 1_000_000;
                    long totalSeconds = durationInMillis / 1000;
                    long minutes = totalSeconds / 60;
                    long seconds = totalSeconds % 60;
                    long milliseconds = durationInMillis % 1000;

                    System.out.println("Execution time for prime check between " + primeRangeLower + " - " + primeRangeUpper + " in: " + minutes + " min " + seconds + " sec " + milliseconds + " ms using " + threadNumber + " threads");
                    System.out.println();                   //Giving the time for each amount thread
                    prime.ID = 2;
                }

                break;
            }
            case 2: {  //Single Search

                prime.option = 2;

                //long[] tests = {7, 11, 53, 83, 101, 577, 1009, 2017, 5003, 10007, 15013, 20011, 50021, 100003, 500009, 1000003, 2000003, 5000011, 10000019};
                long[] tests = {10000000069L};                      //enter the numbers or number you'd like to check

                for (int j = 0; j < tests.length; j++) {

                    int threadMAX = 8;                          // threads number
                    int threadNumber;


                    for (threadNumber = 1; threadNumber <= threadMAX; threadNumber++) {


                        long primeUpper = tests[j];
                        long amount = tests[j];                         //Setting test number to the variables that spilt it and control the program

                        long startTime = System.nanoTime();         //Timer started
                        int addThreads = threadNumber - 1;          // -1 because main is already thread
                        prime[] thread = new prime[addThreads];     //thread classes


                        if (threadNumber > 1) {
                            prime.singlePrime = primeUpper;         //Setting the static variable for single seeker mode for the classes to compare
                            
                            amount = (primeUpper /2) / (threadNumber);      // already halfing the check range and split the values dynamically based on the number of thread

                            long numbersplit = amount + 1;          // +1 to start at the next value



                            for (int i = 0; i < addThreads; i++) {

                                if (i == (addThreads - 1)) {
                                    thread[i] = new prime(numbersplit, (primeUpper / 2));     //to the final checkvalue
                                } else {
                                    thread[i] = new prime(numbersplit, numbersplit + amount);       //adding value range to classes
                                    numbersplit = numbersplit + amount + 1;
                                }
                            }

                        }


                        if (threadNumber > 1) {                 //Starting threads
                            for (int i = 0; i < addThreads; i++) {
                                thread[i].t.start();
                            }
                        }


                        if (threadNumber == 1) amount /= 2;     //for main thread only to give correct test range

                        if (isprimeSingle(tests[j], amount) == false) {         //input is Prime check and the range
                            System.out.println(tests[j] + " Nope... not prime. ");
                        }




                        if (threadNumber > 1) {
                            try {                                               //Waiting for the threads to finish
                                for (int i = 0; i < addThreads; i++) {
                                    thread[i].t.join();
                                }
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        long endTime = System.nanoTime();

                        long durationInMillis = (endTime - startTime) / 1_000_000;          //Calculation timing
                        long totalSeconds = durationInMillis / 1000;
                        long minutes = totalSeconds / 60;
                        long seconds = totalSeconds % 60;
                        long milliseconds = durationInMillis % 1000;

                        System.out.println("Execution time for prime check of " + tests[j] + ": " + minutes + " min " + seconds + " sec " + milliseconds + " ms using " + threadNumber + " threads");
                        System.out.println();                   //Giving the time for each thread
                        prime.ID = 2;
                    }
                }
            }
        }
    }
}
