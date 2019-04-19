package lab4;

import java.awt.Container;
import javax.media.j3d.*; // for transform
import javax.vecmath.Color3f;
import java.awt.Color;
import com.sun.j3d.utils.geometry.*;
import javax.vecmath.*; // for Vector3f
import com.sun.j3d.utils.image.TextureLoader;

public class Cube {
    public static Box Cube() {
        int prim_flags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
        Box box = new Box(0.2f,0.2f, 0.2f, prim_flags, paintItBlack());
        return box;
    }

    public static Box oneColCube(Appearance appearance, int side) {
        Box box = Cube();
        box.setAppearance(side, appearance);
        return box;
    }

    public static Box twoColCube(Appearance appearance1, Appearance appearance2, int side1, int side2) {
        Box box = Cube();
        box.setAppearance(side1, appearance1);
        box.setAppearance(side2, appearance2);
        return box;
    }

    public static Box threeColCube(Appearance appearance1, Appearance appearance2, Appearance appearance3, int side1, int side2, int side3) {
        Box box = Cube();
        box.setAppearance(side1, appearance1);
        box.setAppearance(side2, appearance2);
        box.setAppearance(side3, appearance3);
        return box;
    }

    public static Appearance setTexture(String source_texture){
        // завантажуємо текстуру
        TextureLoader loader = new TextureLoader(source_texture, "LUMINANCE", new Container());
        Texture texture = loader.getTexture();

        // задаємо властивості границі текстури
        texture.setBoundaryModeS(Texture.WRAP);
        texture.setBoundaryModeT(Texture.WRAP);
        texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 1.0f, 0.0f));

        // встановлюємо атрибути текстури
        TextureAttributes texAttr = new TextureAttributes();
        texAttr.setTextureMode(TextureAttributes.MODULATE);

        Appearance ap = new Appearance();
        ap.setTexture(texture);
        ap.setTextureAttributes(texAttr);
        return ap;
    }

    public static Appearance paintItBlack(){
        Appearance ap = new Appearance();
        Color3f emissive = new Color3f(new Color(0,0, 0));
        Color3f ambient = new Color3f(new Color(0,0, 0));
        Color3f diffuse = new Color3f(new Color(0,0, 0));
        Color3f specular = new Color3f(new Color(0,0, 0));
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));
        return ap;
    }

    public static Appearance paintItGreen() {
        Appearance ap = setTexture("source_folder\\green.jpg");
        Color3f emissive = new Color3f(new Color(0,0, 0));
        Color3f ambient = new Color3f(new Color(78,200, 0));
        Color3f diffuse = new Color3f(new Color(78,138, 0));
        Color3f specular = new Color3f(new Color(0,0, 0));
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));
        return ap;
    }

    public static Appearance paintItYellow() {
        Appearance ap = setTexture("source_folder\\yellow.jpg");
        Color3f emissive = new Color3f(new Color(0,0, 0));
        Color3f ambient = new Color3f(new Color(255,255, 0));
        Color3f diffuse = new Color3f(new Color(255,200, 0));
        Color3f specular = new Color3f(new Color(0,0, 0));
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));
        return ap;
    }

    public static Appearance paintItBlue() {
        Appearance ap = setTexture("source_folder\\blue.jpg");
        Color3f emissive = new Color3f(new Color(0,0, 0));
        Color3f ambient = new Color3f(new Color(0,0, 255));
        Color3f diffuse = new Color3f(new Color(0,0, 200));
        Color3f specular = new Color3f(new Color(0,0, 0));
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));
        return ap;
    }

    public static Appearance paintItRed() {
        Appearance ap = setTexture("source_folder\\red.jpg");
        Color3f emissive = new Color3f(new Color(0,0, 0));
        Color3f ambient = new Color3f(new Color(255,0, 0));
        Color3f diffuse = new Color3f(new Color(220,0, 0));
        Color3f specular = new Color3f(new Color(0,0, 0));
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));
        return ap;
    }

    public static Appearance paintItWhite() {
        Appearance ap = setTexture("source_folder\\white.jpg");
        Color3f emissive = new Color3f(new Color(0,0, 0));
        Color3f ambient = new Color3f(new Color(250,250, 250));
        Color3f diffuse = new Color3f(new Color(230,230, 230));
        Color3f specular = new Color3f(new Color(0,0, 0));
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));
        return ap;
    }

    public static Appearance paintItOrange() {
        Appearance ap = setTexture("source_folder\\orange.jpg");
        Color3f emissive = new Color3f(new Color(0,0, 0));
        Color3f ambient = new Color3f(new Color(255,160, 0));
        Color3f diffuse = new Color3f(new Color(255,140, 0));
        Color3f specular = new Color3f(new Color(0,0, 0));
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));
        return ap;
    }
}
