import java.util.*;

class Knapsack {
    public double greedyOnProfit(int objs, int cap, int[] profit, int[] weight, double[] prw) {
        double gop = 0;
        int[] arr = new int[objs];
        int[] p = new int[objs];
        int[] w = new int[objs];
        int bucket = 0;
        int max = 0;
        for (int i = 0; i < profit.length; i++) {
            p[i] = profit[i];
            w[i] = weight[i];
            arr[i] = p[i];
        }
        Arrays.sort(arr);
        for (int i = arr.length - 1; i >= 0 && bucket < cap; i--) {
            max = arr[i];
            for (int j = 0; j < arr.length; j++) {
                if (p[j] == max) {
                    if (bucket + w[j] > cap) {
                        gop += prw[j] * (cap - bucket);
                        bucket = cap;
                    } else {
                        if (w[j] != -1) {
                            bucket += w[j];
                            gop += p[j];
                        } else {
                            continue;
                        }
                        w[j] = p[j] = -1;
                    }
                    break;
                }
            }
        }
        return gop;
    }

    public double greedyOnWeight(int objs, int cap, int[] profit, int[] weight, double[] prw) {
        double gow = 0;
        int[] arr = new int[objs];
        int[] p = new int[objs];
        int[] w = new int[objs];
        int bucket = 0;
        int min = 0;
        for (int i = 0; i < weight.length; i++) {
            p[i] = profit[i];
            w[i] = weight[i];
            arr[i] = w[i];
        }
        Arrays.sort(arr);
        for (int i = 0; i < arr.length && bucket < cap; i++) {
            min = arr[i];
            for (int j = 0; j < arr.length; j++) {
                if (w[j] == min) {
                    if (bucket + w[j] > cap) {
                        double num = Double.parseDouble(String.format("%.3f", prw[j]));
                        gow += num * (cap - bucket);
                        bucket = cap;
                    } else {
                        if (w[j] != -1) {
                            bucket += w[j];
                            gow += p[j];
                        } else {
                            continue;
                        }
                        w[j] = p[j] = -1;
                    }
                    break;
                }
            }
        }
        return gow;
    }

    public double greedyOnPRW(int objs, int cap, int[] profit, int[] weight, double[] prw) {
        double goprw = 0;
        double[] arr = new double[objs];
        int[] p = new int[objs];
        int[] w = new int[objs];
        int bucket = 0;
        double max = 0;
        for (int i = 0; i < weight.length; i++) {
            p[i] = profit[i];
            w[i] = weight[i];
            arr[i] = prw[i];
        }
        Arrays.sort(arr);
        for (int i = arr.length - 1; i >= 0 && bucket < cap; i--) {
            max = arr[i];
            for (int j = 0; j < arr.length; j++) {
                if (prw[j] == max) {
                    if (bucket + w[j] > cap) {
                        double num = Double.parseDouble(String.format("%.3f", prw[j]));
                        goprw += num * (cap - bucket);
                        bucket = cap;
                    } else {
                        if (w[j] != -1) {
                            bucket += w[j];
                            goprw += p[j];
                        } else {
                            continue;
                        }
                        w[j] = p[j] = -1;
                    }
                    break;
                }
            }
        }
        return goprw;
    }

    public String getMostProfitableSolution(int objs, int cap, int[] profit, int[] weight, double[] prw) {
        String str = "";
        double res, gop, gow, goprw;
        gop = greedyOnProfit(objs, cap, profit, weight, prw);
        gow = greedyOnWeight(objs, cap, profit, weight, prw);
        goprw = greedyOnPRW(objs, cap, profit, weight, prw);
        res = gop;
        res = res < gow ? gow : res;
        res = res < goprw ? goprw : res;
        if (gop == res && gow != res && goprw != res) {
            str = "Most profitable solution is Greedy on Profit having profit " + gop;
        } else if (gow == res && gop != res && goprw != res) {
            str = "Most profitable solution is Greedy on Weight having profit " + gow;
        } else if (goprw == res && gop != res && gow != res) {
            str = "Most profitable solution is Greedy on Profit Ratio Weight having profit " + goprw;
        } else if (gop == res && gow == res && goprw != res) {
            str = "Most profitable solution is Greedy on Profit and Greedy on Weight having profit " + gop;
        } else if (gop == res && gow != res && goprw == res) {
            str = "Most profitable solution is Greedy on Profit and Greedy on Profit Ratio Weight having profit " + gop;
        } else if (gop != res && gow == res && goprw == res) {
            str = "Most profitable solution is Greedy on Weight and Greedy on Profit Ratio Weight having profit " + gow;
        } else if (gop == res && gow == res && goprw == res) {
            str = "Most profitable solution is Greedy on Profit, Greedy on Weight and Greedy on Profit Ratio Weight having profit "
                    + gop;
        }
        return str;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Knapsack ks = new Knapsack();
        int objs, cap;
        int[] profit, weight;
        double[] prw;
        do {
            System.out.print("Enter no. of objects: ");
            objs = Integer.parseInt(sc.nextLine());
            if (objs < 1)
                System.out.println("No. of objects should be greater than zero.");
        } while (objs < 1);
        do {
            System.out.print("Enter capacity of the bucket: ");
            cap = Integer.parseInt(sc.nextLine());
            if (cap < 1)
                System.out.println("Capacity of the bucket should be greater than zero.");
        } while (cap < 1);
        profit = new int[objs];
        weight = new int[objs];
        prw = new double[objs];
        for (int i = 0; i < objs; i++) {
            System.out.print("Enter Profit on object " + (i + 1) + ": ");
            profit[i] = Integer.parseInt(sc.nextLine());
            System.out.print("Enter Weight of object " + (i + 1) + ": ");
            weight[i] = Integer.parseInt(sc.nextLine());
            prw[i] = (double) profit[i] / weight[i];
        }
        while (true) {
            System.out.println(
                    "\nProgrammed by Kunal Purswani Roll No. 59.\n\n----------------------- MENU -------------------------\n\tPress 1 for Greedy on Profit\n\tPress 2 for Greedy on Weight\n\tPress 3 for Greedy on Profit Ratio Weight\n\tPress 4 to get Most Profitable Solution.\n\tPress 5 to Exit.\n------------------------------------------------------\n");
            System.out.print("Enter your choice: ");
            int ch = Integer.parseInt(sc.nextLine());
            double res = 0;
            switch (ch) {
                case 1:
                    res = ks.greedyOnProfit(objs, cap, profit, weight, prw);
                    if (Math.floor(res) - res == 0) {
                        System.out.println("\nGreedy On Profit: " + (int) Math.floor(res));
                        System.out.println("\n------------------------------------------------------");
                    } else {
                        System.out.println("\nGreedy On Profit: " + res);
                        System.out.println("\n------------------------------------------------------");
                    }
                    break;
                case 2:
                    res = ks.greedyOnWeight(objs, cap, profit, weight, prw);
                    if (Math.floor(res) - res == 0) {
                        System.out.println("\nGreedy On Weight: " + (int) Math.floor(res));
                        System.out.println("\n------------------------------------------------------");
                    } else {
                        System.out.println("\nGreedy On Weight: " + res);
                        System.out.println("\n------------------------------------------------------");
                    }
                    break;
                case 3:
                    res = ks.greedyOnPRW(objs, cap, profit, weight, prw);
                    if (Math.floor(res) - res == 0) {
                        System.out.println("\nGreedy On Profit Ratio Weight: " + (int) Math.floor(res));
                        System.out.println("\n------------------------------------------------------");
                    } else {
                        System.out.println("\nGreedy On Profit Ratio Weight: " + res);
                        System.out.println("\n------------------------------------------------------");
                    }
                    break;
                case 4:
                    System.out.println("\n" + ks.getMostProfitableSolution(objs, cap, profit, weight, prw));
                    System.out.println("\n------------------------------------------------------");
                    break;
                case 5:
                    System.out.println("\nThankyou for running this program.");
                    System.out.println("\n------------------------------------------------------");
                    System.exit(1);
                    break;
                default:
                    System.out.println("\nPlease make a valid choice.");
                    System.out.println("\n------------------------------------------------------");
                    break;
            }
        }
    }
}