package AltLib;

public class Vec2Math {
    public static float dot(float[] a, float[] b){
        return a[0] * b[0] + a[1] * b[1];
    }

    public static float distance2(float[] a, float[] b){
        return (float)(Math.pow(a[0] - b[0],2) + Math.pow(a[1] - b[1],2));
    }

    public static float distance(float[] v){
        return (float)Math.sqrt(Math.pow(v[0],2) + Math.pow(v[1],2));
    }

    public static float[] normalize(float[] v){
        float distance = distance(v);
        if(distance == 0){
            return new float[]{0,0};
        }
        return new float[]{v[0] / distance,v[1] / distance};
    }

    public static float[] reflectVector(float[] v, float[] normal){
        float[] result = new float[2];
        v = normalize(v);
        normal = normalize(normal);
        result[0] = v[0] - 2 * dot(v,normal) * normal[0];
        result[1] = v[1] - 2 * dot(v,normal) * normal[1];
        return result;
    }
}
