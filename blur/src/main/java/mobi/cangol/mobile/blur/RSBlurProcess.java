/*
 *
 *  Copyright (c) 2013 Cangol
 *   <p/>
 *   Licensed under the Apache License, Version 2.0 (the "License")
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *  <p/>
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  <p/>
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package mobi.cangol.mobile.blur;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;

/**
 * @see JavaBlurProcess
 * Blur using renderscript.
 */
class RSBlurProcess implements BlurProcess {
    private final Context context;
    private final RenderScript _rs;

    public RSBlurProcess(Context context) {
        this.context = context.getApplicationContext();
        _rs = RenderScript.create(this.context);
    }

    @Override
    public Bitmap blur(Bitmap original, float radius) {
        int width = original.getWidth();
        int height = original.getHeight();
        Bitmap blurred = original.copy(Bitmap.Config.ARGB_8888, true);

        ScriptC_blur blurScript = new ScriptC_blur(_rs, context.getResources(), R.raw.blur);

        Allocation inAllocation = Allocation.createFromBitmap(_rs, blurred, Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SCRIPT);

        blurScript.set_gIn(inAllocation);
        blurScript.set_width(width);
        blurScript.set_height(height);
        blurScript.set_radius((int) radius);

        int[] row_indices = new int[height];
        for (int i = 0; i < height; i++) {
            row_indices[i] = i;
        }

        Allocation rows = Allocation.createSized(_rs, Element.U32(_rs), height, Allocation.USAGE_SCRIPT);
        rows.copyFrom(row_indices);

        row_indices = new int[width];
        for (int i = 0; i < width; i++) {
            row_indices[i] = i;
        }

        Allocation columns = Allocation.createSized(_rs, Element.U32(_rs), width, Allocation.USAGE_SCRIPT);
        columns.copyFrom(row_indices);

        blurScript.forEach_blur_h(rows);
        blurScript.forEach_blur_v(columns);
        inAllocation.copyTo(blurred);

        return blurred;
    }
}