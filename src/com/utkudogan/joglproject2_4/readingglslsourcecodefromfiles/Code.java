/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utkudogan.joglproject2_4.readingglslsourcecodefromfiles;

/**
 *
 * @author utkudogan
 */
import javax.swing.*;
import static com.jogamp.opengl.GL4.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class Code extends JFrame implements GLEventListener {

    private GLCanvas myCanvas;
    private int renderingProgram;
    private int vao[] = new int[1];

    public Code() {
        setTitle("Chapter 2 - program 4");
        setSize(600, 400);
        myCanvas = new GLCanvas();
        myCanvas.addGLEventListener(this);
        this.add(myCanvas);
        this.setVisible(true);
    }

    public void display(GLAutoDrawable drawable) {
        GL4 gl = (GL4) GLContext.getCurrentGL();
        gl.glUseProgram(renderingProgram);
        gl.glDrawArrays(GL_POINTS, 0, 1);
    }

    public void init(GLAutoDrawable drawable) {
        GL4 gl = (GL4) GLContext.getCurrentGL();
        renderingProgram = createShaderProgram();
        gl.glGenVertexArrays(vao.length, vao, 0);
        gl.glBindVertexArray(vao[0]);
    }

    private int createShaderProgram() {
        GL4 gl = (GL4) GLContext.getCurrentGL();

        String vshaderSource[] = readShaderSource("src/com/utkudogan/joglproject2_4/readingglslsourcecodefromfiles/vertShader.glsl");
        String fshaderSource[] = readShaderSource("src/com/utkudogan/joglproject2_4/readingglslsourcecodefromfiles/fragShader.glsl");

        int vShader = gl.glCreateShader(GL_VERTEX_SHADER);
        int fShader = gl.glCreateShader(GL_FRAGMENT_SHADER);

        gl.glShaderSource(vShader, vshaderSource.length, vshaderSource, null, 0);
        gl.glCompileShader(vShader);

        gl.glShaderSource(fShader, fshaderSource.length, fshaderSource, null, 0);
        gl.glCompileShader(fShader);

        int vfprogram = gl.glCreateProgram();
        gl.glAttachShader(vfprogram, vShader);
        gl.glAttachShader(vfprogram, fShader);
        gl.glLinkProgram(vfprogram);

        gl.glDeleteShader(vShader);
        gl.glDeleteShader(fShader);
        return vfprogram;
    }

    public static void main(String[] args) {
        new Code();
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public void dispose(GLAutoDrawable drawable) {
    }

    private String[] readShaderSource(String filename) {
        Vector<String> lines = new Vector<String>();
        String[] program;
        Scanner sc;
        try {
            sc = new Scanner(new File(filename));
            while (sc.hasNext()) {
                lines.addElement(sc.nextLine());
            }
            program = new String[lines.size()];
            for (int i = 0; i < lines.size(); i++) {
                program[i] = (String) lines.elementAt(i) + "\n";
            }
        } catch (IOException e) {
            System.err.println("IOException reading file: " + e);
            return null;
        }
        return program;
    }
}
