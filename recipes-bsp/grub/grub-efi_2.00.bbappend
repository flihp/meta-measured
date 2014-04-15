# GRUB_MODS_BUILTIN_append = " multiboot2 relocator acpi gzio"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
    file://unicode.pf2;md5=d15b4716ae6d5a63bb93cadd1dfe8a13 \
"

GRUB_BUILDIN_append = " \
    echo \
    terminal \
    terminfo \
    efi_gop \
    efi_uga \
    gfxterm \
    bitmap \
    gfxmenu \
    trig \
    font \
    multiboot2 \
    relocator \
    acpi \
    gzio \
    serial \
"
