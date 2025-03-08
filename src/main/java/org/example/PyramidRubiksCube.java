package org.example;

public class PyramidRubiksCube {

    // 4 Colors of pyramid cube
    private static char RED = 'R';
    private static char GREEN = 'G';
    private static char BLUE = 'B';
    private static char YELLOW = 'Y';

    // Structure constants
    private static int NUM_FACES = 4;
    private static int TILES_PER_FACE = 9;
    private static int NUM_EDGES = 6;
    private static int NUM_CORNERS = 4;

    // Arrays to represent different parts of the Pyraminx
    private char[][] faces;     // All 9 tiles on each face
    private char[][] edges;     // The 6 edges (each connects 2 faces)
    private char[][] corners;   // The 4 corners (each connects 3 faces)

    /**
     * Constructor to create a new Pyraminx in solved state
     */
    public PyramidRubiksCube() {
        // Initialize the arrays
        faces = new char[NUM_FACES][TILES_PER_FACE];
        edges = new char[NUM_EDGES][2];  // Each edge connects 2 faces
        corners = new char[NUM_CORNERS][3];  // Each corner connects 3 faces

        // Set up the cube
        initializeFaces();
        initializeEdges();
    }

    /**
     * Initializes the faces of the Pyraminx with their respective colors.
     *
     * In a Pyraminx, each face has:
     * - 1 center piece (fixed)
     * - 3 edge pieces
     * - 3 corner pieces
     * - 3 middle pieces
     *
     * Tile layout for each face:
     *       0 (corner)
     *      / \
     *     /   \
     *    1-----2 (edge)
     *   / \   / \
     *  /   \ /   \
     * 3-----4-----5
     *  \   / \   /
     *   \ /   \ /
     *    6-----7-----8
     *
     * where: 4 is the center piece (fixed)
     *        1, 5, 7 are middle pieces
     *        0, 3, 8 are corner pieces
     *        2, 6 are edge pieces
     */
    public void initializeFaces() {
        // Set up the face colors
        char[] faceColors = {RED, GREEN, BLUE, YELLOW};

        for (int face = 0; face < NUM_FACES; face++) {
            // Set all tiles on this face to the face color
            for (int tile = 0; tile < TILES_PER_FACE; tile++) {
                faces[face][tile] = faceColors[face];
            }
        }

        System.out.println("Faces initialized with their respective colors.");
    }

    /**
     * Initializes the edges and corners of the Pyraminx.
     *
     * Edges connect two faces:
     * - Edge 0: connects Red and Green
     * - Edge 1: connects Red and Blue
     * - Edge 2: connects Red and Yellow
     * - Edge 3: connects Green and Blue
     * - Edge 4: connects Green and Yellow
     * - Edge 5: connects Blue and Yellow
     *
     * Corners connect three faces:
     * - Corner 0: connects Red, Green, and Blue
     * - Corner 1: connects Red, Green, and Yellow
     * - Corner 2: connects Red, Blue, and Yellow
     * - Corner 3: connects Green, Blue, and Yellow
     */
    public void initializeEdges() {
        // Each edge connects two faces
        // In a solved state, edge pieces have colors matching their respective faces

        // Edge 0: Red-Green
        edges[0][0] = RED;
        edges[0][1] = GREEN;

        // Edge 1: Red-Blue
        edges[1][0] = RED;
        edges[1][1] = BLUE;

        // Edge 2: Red-Yellow
        edges[2][0] = RED;
        edges[2][1] = YELLOW;

        // Edge 3: Green-Blue
        edges[3][0] = GREEN;
        edges[3][1] = BLUE;

        // Edge 4: Green-Yellow
        edges[4][0] = GREEN;
        edges[4][1] = YELLOW;

        // Edge 5: Blue-Yellow
        edges[5][0] = BLUE;
        edges[5][1] = YELLOW;

        // Initialize corners
        // Corner 0: Red-Green-Blue
        corners[0][0] = RED;
        corners[0][1] = GREEN;
        corners[0][2] = BLUE;

        // Corner 1: Red-Green-Yellow
        corners[1][0] = RED;
        corners[1][1] = GREEN;
        corners[1][2] = YELLOW;

        // Corner 2: Red-Blue-Yellow
        corners[2][0] = RED;
        corners[2][1] = BLUE;
        corners[2][2] = YELLOW;

        // Corner 3: Green-Blue-Yellow
        corners[3][0] = GREEN;
        corners[3][1] = BLUE;
        corners[3][2] = YELLOW;

        System.out.println("Edges and corners initialized with proper colors.");
    }

    /**
     * Validates the cube
     * - Each face should have exactly 9 tiles
     * - Color distribution should be valid
     * - Centers should be fixed
     *
     * @return true if the cube is valid, false otherwise
     */
    public boolean validateCube() {
        int redCount = 0, greenCount = 0, blueCount = 0, yellowCount = 0;

        // Validate faces
        for (int face = 0; face < NUM_FACES; face++) {
            // Check if each face has exactly 9 tiles
            if (faces[face].length != TILES_PER_FACE) {
                System.out.println("Face " + face + " does not have exactly 9 tiles!");
                return false;
            }

            // Count the colors on each face
            for (int tile = 0; tile < TILES_PER_FACE; tile++) {
                char color = faces[face][tile];

                // Count each color
                if (color == RED) redCount++;
                else if (color == GREEN) greenCount++;
                else if (color == BLUE) blueCount++;
                else if (color == YELLOW) yellowCount++;
                else {
                    System.out.println("Invalid color found on face " + face + ", tile " + tile);
                    return false;
                }
            }
        }

        // Check center pieces - they should match the face color in a valid Pyraminx
        char[] faceColors = {RED, GREEN, BLUE, YELLOW};
        for (int face = 0; face < NUM_FACES; face++) {
            if (faces[face][4] != faceColors[face]) {  // Center is at index 4
                System.out.println("Center piece on face " + face + " has incorrect color!");
                return false;
            }
        }

        // Verify color counts
        // - Each color appears exactly 9 times
        boolean validDistribution = (redCount == 9 && greenCount == 9 &&
                blueCount == 9 && yellowCount == 9);

        if (!validDistribution) {
            System.out.println("Color distribution is invalid!");
            System.out.println("Red: " + redCount + ", Green: " + greenCount +
                    ", Blue: " + blueCount + ", Yellow: " + yellowCount);
            return false;
        }

        System.out.println("Cube validation successful!");
        return true;
    }

    /**
     * Displays the current state of the Pyraminx in a triangular pattern.
     */
    public void displayCube() {
        String[] faceNames = {"RED", "GREEN", "BLUE", "YELLOW"};

        System.out.println("\nPYRAMINX CURRENT STATE");
        System.out.println("=====================");

        for (int face = 0; face < NUM_FACES; face++) {
            System.out.println("\nFace " + face + " (" + faceNames[face] + "):");

            // Display in a triangular pattern
            System.out.println("      " + faces[face][0]);
            System.out.println("     / \\");
            System.out.println("    /   \\");
            System.out.println("   " + faces[face][1] + "-----" + faces[face][2]);
            System.out.println("  / \\   / \\");
            System.out.println(" /   \\ /   \\");
            System.out.println(faces[face][3] + "-----" + faces[face][4] + "-----" + faces[face][5]);
            System.out.println(" \\   / \\   /");
            System.out.println("  \\ /   \\ /");
            System.out.println("   " + faces[face][6] + "-----" + faces[face][7] + "-----" + faces[face][8]);
        }

        // Display edge information
        System.out.println("\nEdges:");
        String[] edgeConnections = {
                "Red-Green", "Red-Blue", "Red-Yellow",
                "Green-Blue", "Green-Yellow", "Blue-Yellow"
        };

        for (int edge = 0; edge < NUM_EDGES; edge++) {
            System.out.println("Edge " + edge + " (" + edgeConnections[edge] + "): " +
                    edges[edge][0] + "-" + edges[edge][1]);
        }

        // Display corner information
        System.out.println("\nCorners:");
        String[] cornerConnections = {
                "Red-Green-Blue", "Red-Green-Yellow",
                "Red-Blue-Yellow", "Green-Blue-Yellow"
        };

        for (int corner = 0; corner < NUM_CORNERS; corner++) {
            System.out.println("Corner " + corner + " (" + cornerConnections[corner] + "): " +
                    corners[corner][0] + "-" + corners[corner][1] + "-" + corners[corner][2]);
        }
    }


    public static void main(String[] args) {
        PyramidRubiksCube pyraminx = new PyramidRubiksCube();
        System.out.println("New Pyramid Rubik's Cube Created");

        pyraminx.displayCube();
        boolean isValid = pyraminx.validateCube();
        System.out.println("\nCube isValid: " + isValid);
    }
}
