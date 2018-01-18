# Append to GRUB_BUILDIN only if oe-measured distro
# is used (txt OVERRIDE is available).
GRUB_BUILDIN_append_txt = " \
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
