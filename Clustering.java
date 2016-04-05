//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import Custering.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Custering {
    static int number_of_outliers = 0;

    public Custering() {
    }

    public static void form_clusters(double[][] d, int num_c) {
        double min_pt_d = 99999.0D;
        double[][] clusters = new double[500][1000];
        boolean i = false;
        int j = 0;
        boolean m = false;
        boolean n = false;
        boolean x = false;
        boolean num_clusters = false;
        boolean flag_1 = false;
        boolean flag_2 = false;
        int cur_cluster_pos = 0;
        int temp_1 = 0;
        int temp_2 = 0;

        int var20;
        for(var20 = 0; var20 < 500; ++var20) {
            for(j = 0; j < 500; ++j) {
                clusters[var20][j] = -99999.0D;
            }
        }

        int var24 = 500 - number_of_outliers;
        if(var24 > num_c) {
            for(var20 = 0; var20 < 500; ++var20) {
                for(j = 0; j < 500; ++j) {
                    if(d[var20][j] > 0.0D && d[var20][j] < min_pt_d) {
                        min_pt_d = d[var20][j];
                        temp_1 = var20;
                        temp_2 = j;
                    }
                }
            }

            clusters[cur_cluster_pos][0] = (double)var20;
            clusters[cur_cluster_pos][1] = (double)j;
            d[temp_1][temp_2] = -99999.0D;
            d[temp_2][temp_1] = -99999.0D;
            --var24;
            ++cur_cluster_pos;
        }

        System.out.println("\nNumber of points excluding outliers = " + var24);

        while(true) {
            do {
                label209:
                do {
                    if(var24 <= num_c) {
                        System.out.println("The following clusters have been formed:");
                        var20 = 0;

                        for(j = 0; clusters[var20][j] > 0.0D; ++var20) {
                            j = 0;
                            System.out.print("Cluster " + (var20 + 1) + ": Point ");

                            while(clusters[var20][j] > 0.0D) {
                                System.out.print(clusters[var20][j++] + ", ");
                            }

                            System.out.print("\n");
                        }

                        return;
                    }

                    min_pt_d = 99999.0D;
                    flag_1 = false;
                    temp_1 = 0;
                    flag_2 = false;
                    temp_2 = 0;

                    for(var20 = 0; var20 < 500; ++var20) {
                        for(j = 0; j < 500; ++j) {
                            if(d[var20][j] > 0.0D && d[var20][j] < min_pt_d) {
                                min_pt_d = d[var20][j];
                                temp_1 = var20;
                                temp_2 = j;
                            }
                        }
                    }

                    int var21;
                    int var22;
                    for(var21 = 0; var21 < 500 && clusters[var21][0] >= 0.0D; ++var21) {
                        for(var22 = 0; var22 < 500 && clusters[var21][var22] >= 0.0D; ++var22) {
                            if(clusters[var21][var22] == (double)temp_1) {
                                flag_1 = true;
                                break;
                            }

                            if(clusters[var21][var22] == (double)temp_2) {
                                flag_2 = true;
                                break;
                            }
                        }
                    }

                    if(!flag_1 && !flag_2 && cur_cluster_pos < 500) {
                        clusters[cur_cluster_pos][0] = (double)temp_1;
                        clusters[cur_cluster_pos][1] = (double)temp_2;
                        d[temp_1][temp_2] = -99999.0D;
                        d[temp_2][temp_1] = -99999.0D;
                        --var24;
                        ++cur_cluster_pos;
                    }

                    if(!flag_1 && flag_2) {
                        for(var21 = 0; var21 < 500; ++var21) {
                            for(var22 = 0; var22 < 500; ++var22) {
                                if(clusters[var21][var22] == (double)temp_2) {
                                    while(clusters[var21][var22] > 0.0D) {
                                        ++var22;
                                    }

                                    clusters[var21][var22] = (double)temp_1;
                                    d[temp_1][temp_2] = -99999.0D;
                                    d[temp_2][temp_1] = -99999.0D;
                                    --var24;
                                    continue label209;
                                }
                            }
                        }
                    } else if(flag_1 && !flag_2) {
                        for(var21 = 0; var21 < 500; ++var21) {
                            for(var22 = 0; var22 < 500; ++var22) {
                                if(clusters[var21][var22] == (double)temp_1) {
                                    while(clusters[var21][var22] > 0.0D) {
                                        ++var22;
                                    }

                                    clusters[var21][var22] = (double)temp_2;
                                    d[temp_1][temp_2] = -99999.0D;
                                    d[temp_2][temp_1] = -99999.0D;
                                    --var24;
                                    continue label209;
                                }
                            }
                        }
                    }
                } while(!flag_1);
            } while(!flag_2);

            for(int m_1 = 0; m_1 < 500; ++m_1) {
                for(int n_1 = 0; n_1 < 500; ++n_1) {
                    if(clusters[m_1][n_1] == (double)temp_1) {
                        while(clusters[m_1][n_1] > 0.0D) {
                            ++n_1;
                        }

                        for(int m_2 = 0; m_2 < 500; ++m_2) {
                            for(int n_2 = 0; n_2 < 500; ++n_2) {
                                if(clusters[m_2][n_2] == (double)temp_2) {
                                    while(clusters[m_2][n_2] > 0.0D) {
                                        ++n_2;
                                    }

                                    for(int var23 = 0; var23 < n_2; ++var23) {
                                        clusters[m_1][n_1] = clusters[m_2][var23];
                                        clusters[m_2][var23] = -99999.0D;
                                        ++n_1;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            --var24;
        }
    }

    public static double[][] find_outlier_points(double[][] d, double c_d, double t_p) {
        double counter = 0.0D;
        ArrayList outliers = new ArrayList();

        for(int i = 0; i < 500; ++i) {
            counter = 0.0D;

            int j;
            for(j = 0; j < 500; ++j) {
                if(d[i][j] > c_d) {
                    ++counter;
                }
            }

            counter = counter / 499.0D * 100.0D;
            if(counter > t_p) {
                outliers.add("Point " + (i + 1));
                ++number_of_outliers;

                for(j = 0; j < 500; ++j) {
                    d[i][j] = -99999.0D;
                    d[j][i] = -99999.0D;
                }
            }
        }

        if(outliers.isEmpty()) {
            System.out.println("No Outliers detected !");
        } else {
            System.out.println("Outliers detected based on Min_Distance = " + c_d + " and Threshold = " + t_p + "%");
            System.out.println(outliers);
        }

        return d;
    }

    public static double[][] calc_dist_points(Point[] p) {
        double[][] dist = new double[500][500];

        for(int i = 0; i < 500; ++i) {
            dist[i][i] = -99999.0D;

            for(int j = i + 1; j < 500; ++j) {
                int x = p[i].x - p[j].x;
                int y = p[i].y - p[j].y;
                int z = p[i].z - p[j].z;
                dist[i][j] = Math.pow((double)(x * x + y * y + z * z), 0.5D);
                dist[j][i] = dist[i][j];
            }
        }

        return dist;
    }

    public static Point[] init_points(Point[] p) {
        String fileName = "Input_Data_Used.txt";
        BufferedWriter writer = null;
        System.out.println("Creating file- Input_Data_Used.txt to store the points");

        try {
            File e = new File(fileName);
            if(e.exists() && !e.isDirectory()) {
                System.out.println("File Exists !");
            } else {
                e.createNewFile();
            }

            System.out.println("Note- All points generated are stored in the file: \"" + fileName + "\"");
            System.out.println("at the path: " + e.getCanonicalPath() + "\n");
            writer = new BufferedWriter(new FileWriter(e, true));
            Random r = new Random();

            for(int i = 0; i < 500; ++i) {
                p[i].x = r.nextInt(2000) - 10;
                p[i].y = r.nextInt(4000) + 20;
                p[i].z = r.nextInt(6000) + 30;
                writer.write("Point " + (i + 1) + " = " + "(" + p[i].x + "," + p[i].y + "," + p[i].z + ")\n");
            }
        } catch (Exception var14) {
            var14.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception var13) {
                ;
            }

        }

        return p;
    }

    public static void main(String[] args) throws IOException {
        int numClusters = 0;
        boolean i = false;
        double criticalDistance = 0.0D;
        double thresholdPercentage = 0.0D;
        cleanup();
        Point[] points = new Point[500];

        for(int var15 = 0; var15 < 500; ++var15) {
            points[var15] = new Point(0, 0, 0);
        }

        double[][] distance_matrix = new double[500][500];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter Number of clusters to be formed: ");

        String temp;
        try {
            temp = br.readLine();
            numClusters = Integer.parseInt(temp);
            if(numClusters < 1 || numClusters > 500) {
                throw new Exception();
            }
        } catch (Exception var14) {
            System.out.println("Error: Please enter an integer number between 1-500 !\n");
            System.out.println("Run Program Again...\n");
            System.exit(0);
        }

        System.out.print("Enter Critical Distance for outlier detection: ");

        try {
            temp = br.readLine();
            criticalDistance = Double.parseDouble(temp);
            if(criticalDistance < 0.0D) {
                throw new Exception();
            }
        } catch (Exception var12) {
            System.out.println("Error: Please enter a positive value for distance !\n");
            System.out.println("Run Program Again...\n");
            System.exit(0);
        }

        System.out.print("Enter Threshold(%) for outlier detection: ");

        try {
            temp = br.readLine();
            thresholdPercentage = Double.parseDouble(temp);
            if(thresholdPercentage < 1.0D || thresholdPercentage > 100.0D) {
                throw new Exception();
            }
        } catch (Exception var13) {
            System.out.println("Error: Please enter a number between 1-100 !\n");
            System.out.println("Run Program Again...\n");
            System.exit(0);
        }

        System.out.println("\n****Running HIERARCHICAL AGGLOMERATIVE CLUSTERING Algorithm****\n");
        points = init_points(points);
        System.out.println("STEP-1) Generated 500 random 3-d points\n");
        distance_matrix = calc_dist_points(points);
        System.out.println("STEP-2) Completed distance calculation between all points\n");
        System.out.println("STEP-3) Checking for outliers\n");
        distance_matrix = find_outlier_points(distance_matrix, criticalDistance, thresholdPercentage);
        form_clusters(distance_matrix, numClusters);
    }

    public static void cleanup() throws IOException {
        File file_name = new File("Input_Data_Used.txt");

        try {
            file_name.delete();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }
}
