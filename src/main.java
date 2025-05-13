


public class main
{
    static boolean isprime(long prime) {

        if (prime == 1) return true;
        if (prime == 2) return true;

        if (prime % 2 == 0) return false;
        long halfprime = prime / 2;

        for (int i = 3; i <= halfprime; i += 2) {
            if (prime % i == 0) return false;

        }
        return true;
    }

    public static void main (String[] args)
    {
        int options = 1; //1 RANGE MODE - 2 SINGLE SEARCH

        switch (options) {
            case 1: {  //Range MODE

                long primeRangeLower = 100000;
                long primeRangeUpper = 105000;



                int threadMAX = 8;
                int threadNumber;


                for (threadNumber = 1; threadNumber <= threadMAX; threadNumber++) {
                    long primeLower = primeRangeLower;
                    long primeUpper = primeRangeUpper;


                    long startTime = System.nanoTime();
                    int addThreads = threadNumber - 1;
                    prime[] thread = new prime[addThreads];


                    if (threadNumber > 1) {
                        long amount = (primeUpper - primeLower)/ threadNumber;
                        long numbersplit = amount + primeLower + 1;

                        for (int i = 0; i < addThreads; i++) {

                            if (i == (addThreads - 1)) {
                                thread[i] = new prime(numbersplit, primeUpper);
                            } else {
                                thread[i] = new prime(numbersplit, numbersplit + amount);
                                numbersplit = numbersplit + amount + 1;
                            }
                        }
                        primeUpper = primeLower + amount;
                    }


                    if (threadNumber > 1) {
                        for (int i = 0; i < addThreads; i++) {
                            thread[i].t.start();
                        }
                    }


                    for (primeLower = primeLower; primeLower <= primeUpper; primeLower++) {
                        // if (isprime(primeLower) == true) ;
                        if (isprime(primeLower) == true) System.out.println(primeLower + " is PRIME!!" + " by thread: " + Thread.currentThread().getName());

                    }


                    if (threadNumber > 1) {
                        try {
                            for (int i = 0; i < addThreads; i++) {
                                thread[i].t.join();
                            }
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    long endTime = System.nanoTime();

                    long durationInMillis = (endTime - startTime) / 1_000_000;
                    long totalSeconds = durationInMillis / 1000;
                    long minutes = totalSeconds / 60;
                    long seconds = totalSeconds % 60;
                    long milliseconds = durationInMillis % 1000;

                    System.out.println("Execution time for prime check between " + primeLower + " - " + primeUpper + " in: " + minutes + " min " + seconds + " sec " + milliseconds + " ms using " + threadNumber + " threads");
                    System.out.println();
                    prime.ID = 2;
                }

                break;
            }
            case 2: {  //Single Search

                long[] tests = {7, 11, 53, 83, 101, 577, 1009, 2017, 5003, 10007, 15013, 20011, 50021, 100003, 500009, 1000003, 2000003, 5000011, 10000019};
                //ong[] tests = {1000003};

                for (int j = 0; j < tests.length; j++) {

                    int threadMAX = 16;
                    int threadNumber;


                    for (threadNumber = 1; threadNumber <= threadMAX; threadNumber++) {


                        long primeLower = 0;
                        long primeUpper = tests[j];

                        long startTime = System.nanoTime();
                        int addThreads = threadNumber - 1;
                        prime[] thread = new prime[addThreads];


                        if (threadNumber > 1) {
                            long amount = primeUpper / (threadNumber);
                            long numbersplit = amount + primeLower + 1;

                            for (int i = 0; i < addThreads; i++) {

                                if (i == (addThreads - 1)) {
                                    thread[i] = new prime(numbersplit, primeUpper);
                                } else {
                                    thread[i] = new prime(numbersplit, numbersplit + amount);
                                    numbersplit = numbersplit + amount + 1;
                                }
                            }
                            primeUpper = primeLower + amount;
                        }


                        if (threadNumber > 1) {
                            for (int i = 0; i < addThreads; i++) {
                                thread[i].t.start();
                            }
                        }


                        for (primeLower = primeLower; primeLower <= primeUpper; primeLower++) {
                            // if (isprime(primeLower) == true) ;
                            if (isprime(primeLower) == true) System.out.println(primeLower + " is PRIME!!");

                        }


                        if (threadNumber > 1) {
                            try {
                                for (int i = 0; i < addThreads; i++) {
                                    thread[i].t.join();
                                }
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        long endTime = System.nanoTime();

                        long durationInMillis = (endTime - startTime) / 1_000_000;
                        long totalSeconds = durationInMillis / 1000;
                        long minutes = totalSeconds / 60;
                        long seconds = totalSeconds % 60;
                        long milliseconds = durationInMillis % 1000;

                        System.out.println("Execution time for prime check of " + tests[j] + ": " + minutes + " min " + seconds + " sec " + milliseconds + " ms using " + threadNumber + " threads");

                        prime.ID = 2;
                    }
                }
            }
        }
    }
}
