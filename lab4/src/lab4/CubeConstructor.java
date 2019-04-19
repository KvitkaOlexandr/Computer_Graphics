package lab4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.*;
import javax.swing.Timer;
import javax.vecmath.*;

// Vector3f - float, Vector3d - double
public class CubeConstructor implements ActionListener {
    private float upperEyeLimit = 5.0f; // 5.0
    private float lowerEyeLimit = 1.0f; // 1.0
    private float farthestEyeLimit = 6.0f; // 6.0
    private float nearestEyeLimit = 3.0f; // 3.0

    private TransformGroup treeTransformGroup;
    private TransformGroup viewingTransformGroup;
    private Transform3D treeTransform3D = new Transform3D();
    private Transform3D viewingTransform = new Transform3D();
    private float angle = 0;
    private float eyeHeight;
    private float eyeDistance;
    private boolean descend = true;
    private boolean approaching = true;

    public static void main(String[] args) {
        new CubeConstructor();
    }

    private CubeConstructor() {
        Timer timer = new Timer(50, this);
        SimpleUniverse universe = new SimpleUniverse();

        viewingTransformGroup = universe.getViewingPlatform().getViewPlatformTransform();
        universe.addBranchGraph(createSceneGraph());

        eyeHeight = upperEyeLimit;
        eyeDistance = farthestEyeLimit;
        timer.start();
    }

    private BranchGroup createSceneGraph() {
        BranchGroup objRoot = new BranchGroup();


        treeTransformGroup = new TransformGroup();
        treeTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        build();
        objRoot.addChild(treeTransformGroup);

        Background background = new Background(new Color3f(1.0f, 1.0f, 1.0f)); // white color
        BoundingSphere sphere = new BoundingSphere(new Point3d(0,0,0), 100000);
        background.setApplicationBounds(sphere);
        objRoot.addChild(background);

        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),100.0);
        Color3f light1Color = new Color3f(1.0f, 0.5f, 0.4f);
        Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
        DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
        light1.setInfluencingBounds(bounds);
        objRoot.addChild(light1);

        Color3f ambientColor = new Color3f(1.0f, 1.0f, 1.0f);
        AmbientLight ambientLightNode = new AmbientLight(ambientColor);
        ambientLightNode.setInfluencingBounds(bounds);
        objRoot.addChild(ambientLightNode);
        return objRoot;
    }

    private void build() {
        Box part1 = Cube.threeColCube(Cube.paintItOrange(), Cube.paintItWhite(), Cube.paintItBlue(), 2, 4, 1);
        treeTransformGroup.addChild(makeTG(part1, 0.0f, 0.0f, 0.0f));

        Box part2 = Cube.twoColCube(Cube.paintItRed(), Cube.paintItWhite(), 4, 1);
        treeTransformGroup.addChild(makeTG(part2, -0.4f, 0.0f, 0.0f));

        Box part3 = Cube.threeColCube(Cube.paintItBlue(), Cube.paintItRed(), Cube.paintItGreen(), 4, 3, 1);
        treeTransformGroup.addChild(makeTG(part3, -0.8f, 0.0f, 0.0f));

        Box part4 = Cube.twoColCube(Cube.paintItYellow(), Cube.paintItWhite(), 2, 1);
        treeTransformGroup.addChild(makeTG(part4, 0.0f, -0.4f, 0.0f));

        Box part5 = Cube.oneColCube(Cube.paintItWhite(), 1);
        treeTransformGroup.addChild(makeTG(part5, -0.4f, -0.4f, 0.0f));

        Box part6 = Cube.twoColCube(Cube.paintItGreen(), Cube.paintItOrange(), 3, 1);
        treeTransformGroup.addChild(makeTG(part6, -0.8f, -0.4f, 0.0f));

        Box part7 = Cube.threeColCube(Cube.paintItGreen(), Cube.paintItRed(), Cube.paintItBlue(), 2, 1, 5);
        treeTransformGroup.addChild(makeTG(part7, 0.0f, -0.8f, 0.0f));

        Box part8 = Cube.twoColCube(Cube.paintItWhite(), Cube.paintItBlue(), 5, 1);
        treeTransformGroup.addChild(makeTG(part8, -0.4f, -0.8f, 0.0f));

        Box part9 = Cube.threeColCube(Cube.paintItGreen(), Cube.paintItRed(), Cube.paintItYellow(), 5, 3, 1);
        treeTransformGroup.addChild(makeTG(part9, -0.8f, -0.8f, 0.0f));

        Box part10 = Cube.twoColCube(Cube.paintItGreen(), Cube.paintItRed(), 2, 4);
        treeTransformGroup.addChild(makeTG(part10, 0.0f, 0.0f, 0.4f));

        Box part11 = Cube.oneColCube(Cube.paintItYellow(), 4);
        treeTransformGroup.addChild(makeTG(part11, -0.4f, 0.0f, 0.4f));

        Box part12 = Cube.twoColCube(Cube.paintItWhite(), Cube.paintItYellow(), 4, 3);
        treeTransformGroup.addChild(makeTG(part12, -0.8f, 0.0f, 0.4f));

        Box part13 = Cube.oneColCube(Cube.paintItRed(), 2);
        treeTransformGroup.addChild(makeTG(part13, 0.0f, -0.4f, 0.4f));

        Box part14 = Cube.oneColCube(Cube.paintItOrange(), 3);
        treeTransformGroup.addChild(makeTG(part14, -0.8f, -0.4f, 0.4f));

        Box part15 = Cube.twoColCube(Cube.paintItOrange(), Cube.paintItYellow(), 2, 5);
        treeTransformGroup.addChild(makeTG(part15, 0.0f, -0.8f, 0.4f));

        Box part16 = Cube.oneColCube(Cube.paintItBlue(), 5);
        treeTransformGroup.addChild(makeTG(part16, -0.4f, -0.8f, 0.4f));

        Box part17 = Cube.twoColCube(Cube.paintItRed(), Cube.paintItYellow(), 5, 3);
        treeTransformGroup.addChild(makeTG(part17, -0.8f, -0.8f, 0.4f));

        Box part18 = Cube.threeColCube(Cube.paintItBlue(), Cube.paintItRed(), Cube.paintItWhite(), 0, 2, 4);
        treeTransformGroup.addChild(makeTG(part18, 0.0f, 0.0f, 0.8f));

        Box part19 = Cube.twoColCube(Cube.paintItBlue(), Cube.paintItWhite(), 2, 0);
        treeTransformGroup.addChild(makeTG(part19, 0.0f, -0.4f, 0.8f));

        Box part20 = Cube.threeColCube(Cube.paintItYellow(), Cube.paintItRed(), Cube.paintItWhite(), 2, 0, 5);
        treeTransformGroup.addChild(makeTG(part20, 0.0f, -0.8f, 0.8f));

        Box part21 = Cube.twoColCube(Cube.paintItGreen(), Cube.paintItOrange(), 4, 0);
        treeTransformGroup.addChild(makeTG(part21, -0.4f, 0.0f, 0.8f));

        Box part22 = Cube.oneColCube(Cube.paintItGreen(), 0);
        treeTransformGroup.addChild(makeTG(part22, -0.4f, -0.4f, 0.8f));

        Box part23 = Cube.twoColCube(Cube.paintItRed(), Cube.paintItBlue(), 5, 0);
        treeTransformGroup.addChild(makeTG(part23, -0.4f, -0.8f, 0.8f));

        Box part24 = Cube.threeColCube(Cube.paintItYellow(), Cube.paintItOrange(), Cube.paintItGreen(), 4, 3, 0);
        treeTransformGroup.addChild(makeTG(part24, -0.8f, 0.0f, 0.8f));

        Box part25 = Cube.twoColCube(Cube.paintItGreen(), Cube.paintItBlue(), 3, 0);
        treeTransformGroup.addChild(makeTG(part25, -0.8f, -0.4f, 0.8f));

        Box part26 = Cube.threeColCube(Cube.paintItBlue(), Cube.paintItOrange(), Cube.paintItGreen(), 3, 0, 5);
        treeTransformGroup.addChild(makeTG(part26, -0.8f, -0.8f, 0.8f));
    }

    private TransformGroup makeTG(Box box, float x, float y, float z){
        Transform3D partT = new Transform3D();
        partT.setTranslation(new Vector3f(x, y, z));
        TransformGroup partTG = new TransformGroup();
        partTG.setTransform(partT);
        partTG.addChild(box);
        return partTG;
    }

    // ActionListener interface
    @Override
    public void actionPerformed(ActionEvent e) {
        float delta = 0.01f;

        // rotation of the castle
        treeTransform3D.rotZ(angle);
        treeTransformGroup.setTransform(treeTransform3D);
        angle += delta;

        // change of the camera position up and down within defined limits
        if (eyeHeight > upperEyeLimit){
            descend = true;
        }else if(eyeHeight < lowerEyeLimit){
            descend = false;
        }
        if (descend){
            eyeHeight -= delta;
        }else{
            eyeHeight += delta;
        }

        // change camera distance to the scene
        if (eyeDistance > farthestEyeLimit){
            approaching = true;
        }else if(eyeDistance < nearestEyeLimit){
            approaching = false;
        }
        if (approaching){
            eyeDistance -= delta;
        }else{
            eyeDistance += delta;
        }

        Point3d eye = new Point3d(eyeDistance, eyeDistance, eyeHeight); // spectator's eye
        Point3d center = new Point3d(.0f, .0f ,0.5f); // sight target
        Vector3d up = new Vector3d(.0f, .0f, 1.0f);; // the camera frustum
        viewingTransform.lookAt(eye, center, up);
        viewingTransform.invert();
        viewingTransformGroup.setTransform(viewingTransform);
    }
}
