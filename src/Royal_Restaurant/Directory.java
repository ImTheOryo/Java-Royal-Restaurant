package Royal_Restaurant;

import java.io.File;
import java.util.Arrays;

    public class Directory {
        public int biggestDirectory = 1;
        private String path = System.getProperty("user.dir") + "/src/Royal_Restaurant/";


        public int getBiggestRestaurantDirectory () {
            File directory = new File(path);
            if (directory.exists() && directory.isDirectory()) {
                File[] subDirectory = directory.listFiles(File::isDirectory);
                if (subDirectory != null) {
                    int largestDirectoryNumber = 0;
                    for(File subDir : subDirectory) {
                            int dirNumber = Integer.parseInt(subDir.getName());
                            if (dirNumber > largestDirectoryNumber) {
                                largestDirectoryNumber = dirNumber;
                            }
                    }
                    return largestDirectoryNumber;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        }

        public File[] getAllRestaurantDirectory () {
            File directory = new File(path);
            if (directory.exists() && directory.isDirectory()) {
                File[] subDirectory = directory.listFiles(File::isDirectory);
                Arrays.sort(subDirectory);
                return subDirectory;
            } else {
                File[] nothing = null;
                return nothing;
            }
        }

        public File getSpecificRestaurantDirectory (String directoryName) {
            path += directoryName;

            File directory = new File(path);



            if (directory.exists() && directory.isDirectory()) {
                File subDirectory = directory;
                return subDirectory;
            } else {
                File nothing = null;
                return nothing;
            }

        }

        public int getBiggestEmployeeID (String directoryRestaurant) {
            path += directoryRestaurant + "/employees/";
            File directory = new File(path);
            if (directory.exists() && directory.isDirectory()) {
                File[] subDirectory = directory.listFiles(File::isFile);
                if (subDirectory != null) {
                    int largestDirectoryNumber = 0;
                    for(File subDir : subDirectory) {
                        String subDirName = subDir.getName();
                        subDirName = subDirName.replace(".json", "");
                        int dirNumber = Integer.parseInt(subDirName);
                        if (dirNumber > largestDirectoryNumber) {
                            largestDirectoryNumber = dirNumber;
                        }
                    }
                    return largestDirectoryNumber;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        }

        public File[] getAllEmployee (String directoryRestaurant) {
            path += directoryRestaurant + "/employees/";
            File directory = new File(path);
            if (directory.exists() && directory.isDirectory()) {
                File[] subDirectory = directory.listFiles(File::isFile);
                Arrays.sort(subDirectory);
                return subDirectory;
            } else {
                File[] nothing = null;
                return nothing;
            }
        }


        public File[] getAllMenusFromRestaurant(String restaurantId) {
            path += restaurantId + "/menus/";
            File directory = new File(path);
            if (directory.exists() && directory.isDirectory()) {
                File[] subDirectory = directory.listFiles(File::isDirectory);
                Arrays.sort(subDirectory);
                return subDirectory;
            } else {
                File[] nothing = null;
                return nothing;
            }
        }


        public int getBiggestMenuID (String directoryRestaurant) {
            path += directoryRestaurant + "/menus/";
            File directory = new File(path);
            if (directory.exists() && directory.isDirectory()) {
                File[] subDirectory = directory.listFiles(File::isDirectory);
                if (subDirectory != null) {
                    int largestDirectoryNumber = 0;
                    for(File subDir : subDirectory) {
                        String subDirName = subDir.getName();
                        int dirNumber = Integer.parseInt(subDirName);
                        if (dirNumber > largestDirectoryNumber) {
                            largestDirectoryNumber = dirNumber;
                        }
                    }
                    return largestDirectoryNumber;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        }

    }
