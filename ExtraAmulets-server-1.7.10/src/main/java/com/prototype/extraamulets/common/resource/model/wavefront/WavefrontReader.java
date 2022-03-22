/*
 * Decompiled with CFR 0.152.
 */
package com.prototype.extraamulets.common.resource.model.wavefront;

import com.prototype.extraamulets.common.resource.model.wavefront.WavefrontMesh;
import com.prototype.extraamulets.common.resource.model.wavefront.WavefrontModel;
import com.prototype.extraamulets.common.resource.model.wavefront.WavefrontPolygon;
import com.prototype.extraamulets.common.resource.model.wavefront.WavefrontVector;
import com.prototype.extraamulets.common.resource.model.wavefront.WavefrontVertex;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class WavefrontReader {
    private static byte[] bytearr = null;
    private static char[] chararr = null;

    public static WavefrontModel read(ResourceLocation path) throws Exception {
        return WavefrontReader.read(Minecraft.getMinecraft().getResourceManager().getResource(path).getInputStream());
    }

    public static WavefrontModel read(File file) throws Exception {
        try (FileInputStream stream = new FileInputStream(file);){
            WavefrontModel wavefrontModel = WavefrontReader.read(stream);
            return wavefrontModel;
        }
    }

    public static WavefrontModel read(InputStream is) throws Exception {
        try (DataInputStream stream = new DataInputStream(is);){
            stream.skipBytes(35);
            WavefrontModel wavefrontModel = WavefrontReader.read(stream);
            return wavefrontModel;
        }
    }

    private static WavefrontModel read(DataInputStream dis) throws Exception {
        int i;
        WavefrontVector[] positions = new WavefrontVector[dis.readShort()];
        for (i = 0; i < positions.length; ++i) {
            positions[i] = WavefrontVector.of(dis.readFloat(), dis.readFloat(), dis.readFloat());
        }
        WavefrontVector[] normals = new WavefrontVector[dis.readShort()];
        for (i = 0; i < normals.length; ++i) {
            normals[i] = WavefrontVector.of(dis.readFloat(), dis.readFloat(), dis.readFloat());
        }
        WavefrontVector[] textures = new WavefrontVector[dis.readShort()];
        for (i = 0; i < textures.length; ++i) {
            float u = dis.readFloat();
            float v = dis.readFloat();
            float w = dis.readFloat();
            textures[i] = WavefrontVector.of(u, v, w);
        }
        WavefrontMesh[] meshes = new WavefrontMesh[dis.readShort()];
        for (int i2 = 0; i2 < meshes.length; ++i2) {
            meshes[i2] = WavefrontReader.readMesh(dis, positions, normals, textures);
        }
        return new WavefrontModel(meshes);
    }

    protected static byte[] getByteArray(int size) {
        if (bytearr == null || bytearr.length < size) {
            bytearr = new byte[size];
        }
        return bytearr;
    }

    protected static char[] getCharArray(int size) {
        if (chararr == null || chararr.length < size) {
            chararr = new char[size];
        }
        return chararr;
    }

    private static String readString(DataInputStream stream) throws Exception {
        int strlen = stream.readUnsignedShort();
        byte[] bytearr = WavefrontReader.getByteArray(strlen * 3 * 2);
        char[] chararr = WavefrontReader.getCharArray(strlen * 2);
        stream.readFully(bytearr, 0, strlen * 3);
        for (int i = 0; i < strlen; ++i) {
            chararr[i] = (char)((bytearr[i * 3 + 1] & 0xF) << 12 | (bytearr[i * 3 + 2] & 0x3F) << 6 | (bytearr[i * 3 + 0] & 0x3F) << 0);
        }
        return new String(chararr, 0, strlen);
    }

    private static WavefrontMesh readMesh(DataInputStream stream, WavefrontVector[] positions, WavefrontVector[] normals, WavefrontVector[] textures) throws Exception {
        String title = WavefrontReader.readString(stream);
        WavefrontPolygon[] polygons = new WavefrontPolygon[stream.readShort()];
        for (int i = 0; i < polygons.length; ++i) {
            polygons[i] = WavefrontReader.readPolygon(stream, positions, normals, textures);
        }
        return new WavefrontMesh(title, polygons);
    }

    private static WavefrontPolygon readPolygon(DataInputStream stream, WavefrontVector[] positions, WavefrontVector[] normals, WavefrontVector[] textures) throws Exception {
        WavefrontVertex[] vertices = new WavefrontVertex[stream.readShort()];
        for (int i = 0; i < vertices.length; ++i) {
            vertices[i] = WavefrontReader.readVertex(stream, positions, normals, textures);
        }
        return new WavefrontPolygon(vertices);
    }

    private static WavefrontVertex readVertex(DataInputStream stream, WavefrontVector[] positions, WavefrontVector[] normals, WavefrontVector[] textures) throws Exception {
        switch (stream.readByte()) {
            case 0: {
                return new WavefrontVertex(positions[stream.readShort()], null, null);
            }
            case 1: {
                return new WavefrontVertex(positions[stream.readShort()], normals[stream.readShort()], null);
            }
            case 2: {
                return new WavefrontVertex(positions[stream.readShort()], null, textures[stream.readShort()]);
            }
            case 3: {
                return new WavefrontVertex(positions[stream.readShort()], normals[stream.readShort()], textures[stream.readShort()]);
            }
        }
        throw new IllegalArgumentException("Unknown vertex format");
    }
}

