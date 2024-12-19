<template>
    <div class="all grid">
        <i class="pi pi-bars bars"></i>
        <div class="icon-container">
            <i class="pi pi-sun icon"></i>
            <i class="pi pi-cog icon"></i>
            <i class="pi pi-user icon"></i>
        </div>
    </div>
</template>

<style>
    .all {
        color: white;
        background-color: #569191;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding-top: 1rem;
        padding-left: 1rem;
        padding-right: 1rem;

    }

    .bars {
        font-size: 2rem;
        color: white;
    }

    .icon-container {
        display: flex;
        gap: 1.7rem;
    }

    .icon {
        font-size: 2rem;
        color: white;
    }
</style>
