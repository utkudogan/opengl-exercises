/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.utkudogan.joglproject2_1.simple_application;

/**
 *
 * @author bites
 */
import javax.swing.*;
import static com.jogamp.opengl.GL4.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;

public class Code extends JFrame implements GLEventListener {

    private GLCanvas myCanvas;
    //GLCanvas that is compatible with the standard
    //Java JFrame, and on which we can draw 3D scenes.

    public Code() {
        setTitle("Chapter 2 - program 1");
        setSize(600, 400);
        setLocation(200, 200);
        myCanvas = new GLCanvas();
        myCanvas.addGLEventListener(this);
        this.add(myCanvas);
        this.setVisible(true);
    }

    //The display() method is where we place code
    //that draws to the GLCanvas
    @Override
    public void display(GLAutoDrawable drawable) {
        GL4 gl = (GL4) GLContext.getCurrentGL();
        //GL4 is a Java interface to the OpenGL functions

        gl.glClearColor(0.0f, 1.0f, 0.0f, 1.0f);
        // this specifies the value placed in the elements of a color buffer when it is cleared
        gl.glClear(GL_COLOR_BUFFER_BIT);
        // OpenGL has several color buffers, and this command clears all of them—that is,
        // it fills them with a predefined color called the “clear color.”
    }

    public static void main(String[] args) {
        new Code();
    }

    @Override
    public void init(GLAutoDrawable drawable) {
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        //function is called when a GLCanvas is resized
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        // is called when the application exits
    }
}
