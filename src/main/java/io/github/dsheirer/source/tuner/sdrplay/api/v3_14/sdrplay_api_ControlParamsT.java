/*
 * *****************************************************************************
 * Copyright (C) 2014-2024 Dennis Sheirer
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 * ****************************************************************************
 */

// Generated by jextract

package io.github.dsheirer.source.tuner.sdrplay.api.v3_14;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SegmentAllocator;
import java.lang.foreign.SegmentScope;
import java.lang.foreign.StructLayout;
import java.lang.invoke.VarHandle;
/**
 * {@snippet :
 * struct {
 *     sdrplay_api_DcOffsetT dcOffset;
 *     sdrplay_api_DecimationT decimation;
 *     sdrplay_api_AgcT agc;
 *     sdrplay_api_AdsbModeT adsbMode;
 * };
 * }
 */
public class sdrplay_api_ControlParamsT {

    static final StructLayout $struct$LAYOUT = MemoryLayout.structLayout(
        MemoryLayout.structLayout(
            Constants$root.C_CHAR$LAYOUT.withName("DCenable"),
            Constants$root.C_CHAR$LAYOUT.withName("IQenable")
        ).withName("dcOffset"),
        MemoryLayout.structLayout(
            Constants$root.C_CHAR$LAYOUT.withName("enable"),
            Constants$root.C_CHAR$LAYOUT.withName("decimationFactor"),
            Constants$root.C_CHAR$LAYOUT.withName("wideBandSignal")
        ).withName("decimation"),
        MemoryLayout.paddingLayout(24),
        MemoryLayout.structLayout(
            Constants$root.C_INT$LAYOUT.withName("enable"),
            Constants$root.C_INT$LAYOUT.withName("setPoint_dBfs"),
            Constants$root.C_SHORT$LAYOUT.withName("attack_ms"),
            Constants$root.C_SHORT$LAYOUT.withName("decay_ms"),
            Constants$root.C_SHORT$LAYOUT.withName("decay_delay_ms"),
            Constants$root.C_SHORT$LAYOUT.withName("decay_threshold_dB"),
            Constants$root.C_INT$LAYOUT.withName("syncUpdate")
        ).withName("agc"),
        Constants$root.C_INT$LAYOUT.withName("adsbMode")
    );
    public static MemoryLayout $LAYOUT() {
        return sdrplay_api_ControlParamsT.$struct$LAYOUT;
    }
    public static MemorySegment dcOffset$slice(MemorySegment seg) {
        return seg.asSlice(0, 2);
    }
    public static MemorySegment decimation$slice(MemorySegment seg) {
        return seg.asSlice(2, 3);
    }
    public static MemorySegment agc$slice(MemorySegment seg) {
        return seg.asSlice(8, 20);
    }
    static final VarHandle adsbMode$VH = $struct$LAYOUT.varHandle(MemoryLayout.PathElement.groupElement("adsbMode"));
    public static VarHandle adsbMode$VH() {
        return sdrplay_api_ControlParamsT.adsbMode$VH;
    }
    /**
     * Getter for field:
     * {@snippet :
     * sdrplay_api_AdsbModeT adsbMode;
     * }
     */
    public static int adsbMode$get(MemorySegment seg) {
        return (int)sdrplay_api_ControlParamsT.adsbMode$VH.get(seg);
    }
    /**
     * Setter for field:
     * {@snippet :
     * sdrplay_api_AdsbModeT adsbMode;
     * }
     */
    public static void adsbMode$set(MemorySegment seg, int x) {
        sdrplay_api_ControlParamsT.adsbMode$VH.set(seg, x);
    }
    public static int adsbMode$get(MemorySegment seg, long index) {
        return (int)sdrplay_api_ControlParamsT.adsbMode$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void adsbMode$set(MemorySegment seg, long index, int x) {
        sdrplay_api_ControlParamsT.adsbMode$VH.set(seg.asSlice(index*sizeof()), x);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocateArray(long len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment ofAddress(MemorySegment addr, SegmentScope scope) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, scope); }
}

