package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the `libs` extension.
*/
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final AndroidxLibraryAccessors laccForAndroidxLibraryAccessors = new AndroidxLibraryAccessors(owner);
    private final ComLibraryAccessors laccForComLibraryAccessors = new ComLibraryAccessors(owner);
    private final OrgLibraryAccessors laccForOrgLibraryAccessors = new OrgLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(providers, config);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers) {
        super(config, providers);
    }

        /**
         * Creates a dependency provider for junit (junit:junit)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getJunit() { return create("junit"); }

    /**
     * Returns the group of libraries at androidx
     */
    public AndroidxLibraryAccessors getAndroidx() { return laccForAndroidxLibraryAccessors; }

    /**
     * Returns the group of libraries at com
     */
    public ComLibraryAccessors getCom() { return laccForComLibraryAccessors; }

    /**
     * Returns the group of libraries at org
     */
    public OrgLibraryAccessors getOrg() { return laccForOrgLibraryAccessors; }

    /**
     * Returns the group of versions at versions
     */
    public VersionAccessors getVersions() { return vaccForVersionAccessors; }

    /**
     * Returns the group of bundles at bundles
     */
    public BundleAccessors getBundles() { return baccForBundleAccessors; }

    /**
     * Returns the group of plugins at plugins
     */
    public PluginAccessors getPlugins() { return paccForPluginAccessors; }

    public static class AndroidxLibraryAccessors extends SubDependencyFactory {
        private final AndroidxActivityLibraryAccessors laccForAndroidxActivityLibraryAccessors = new AndroidxActivityLibraryAccessors(owner);
        private final AndroidxAnnotationLibraryAccessors laccForAndroidxAnnotationLibraryAccessors = new AndroidxAnnotationLibraryAccessors(owner);
        private final AndroidxAppcompatLibraryAccessors laccForAndroidxAppcompatLibraryAccessors = new AndroidxAppcompatLibraryAccessors(owner);
        private final AndroidxArchLibraryAccessors laccForAndroidxArchLibraryAccessors = new AndroidxArchLibraryAccessors(owner);
        private final AndroidxCollectionLibraryAccessors laccForAndroidxCollectionLibraryAccessors = new AndroidxCollectionLibraryAccessors(owner);
        private final AndroidxComposeLibraryAccessors laccForAndroidxComposeLibraryAccessors = new AndroidxComposeLibraryAccessors(owner);
        private final AndroidxConcurrentLibraryAccessors laccForAndroidxConcurrentLibraryAccessors = new AndroidxConcurrentLibraryAccessors(owner);
        private final AndroidxConstraintlayoutLibraryAccessors laccForAndroidxConstraintlayoutLibraryAccessors = new AndroidxConstraintlayoutLibraryAccessors(owner);
        private final AndroidxCoreLibraryAccessors laccForAndroidxCoreLibraryAccessors = new AndroidxCoreLibraryAccessors(owner);
        private final AndroidxCustomviewLibraryAccessors laccForAndroidxCustomviewLibraryAccessors = new AndroidxCustomviewLibraryAccessors(owner);
        private final AndroidxEmoji2LibraryAccessors laccForAndroidxEmoji2LibraryAccessors = new AndroidxEmoji2LibraryAccessors(owner);
        private final AndroidxLegacyLibraryAccessors laccForAndroidxLegacyLibraryAccessors = new AndroidxLegacyLibraryAccessors(owner);
        private final AndroidxLifecycleLibraryAccessors laccForAndroidxLifecycleLibraryAccessors = new AndroidxLifecycleLibraryAccessors(owner);
        private final AndroidxNavigationLibraryAccessors laccForAndroidxNavigationLibraryAccessors = new AndroidxNavigationLibraryAccessors(owner);
        private final AndroidxResourceinspectionLibraryAccessors laccForAndroidxResourceinspectionLibraryAccessors = new AndroidxResourceinspectionLibraryAccessors(owner);
        private final AndroidxSavedstateLibraryAccessors laccForAndroidxSavedstateLibraryAccessors = new AndroidxSavedstateLibraryAccessors(owner);
        private final AndroidxStartupLibraryAccessors laccForAndroidxStartupLibraryAccessors = new AndroidxStartupLibraryAccessors(owner);
        private final AndroidxTestLibraryAccessors laccForAndroidxTestLibraryAccessors = new AndroidxTestLibraryAccessors(owner);
        private final AndroidxVectordrawableLibraryAccessors laccForAndroidxVectordrawableLibraryAccessors = new AndroidxVectordrawableLibraryAccessors(owner);

        public AndroidxLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for autofill (androidx.autofill:autofill)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getAutofill() { return create("androidx.autofill"); }

            /**
             * Creates a dependency provider for cardview (androidx.cardview:cardview)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCardview() { return create("androidx.cardview"); }

            /**
             * Creates a dependency provider for coordinatorlayout (androidx.coordinatorlayout:coordinatorlayout)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCoordinatorlayout() { return create("androidx.coordinatorlayout"); }

            /**
             * Creates a dependency provider for cursoradapter (androidx.cursoradapter:cursoradapter)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCursoradapter() { return create("androidx.cursoradapter"); }

            /**
             * Creates a dependency provider for documentfile (androidx.documentfile:documentfile)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getDocumentfile() { return create("androidx.documentfile"); }

            /**
             * Creates a dependency provider for drawerlayout (androidx.drawerlayout:drawerlayout)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getDrawerlayout() { return create("androidx.drawerlayout"); }

            /**
             * Creates a dependency provider for dynamicanimation (androidx.dynamicanimation:dynamicanimation)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getDynamicanimation() { return create("androidx.dynamicanimation"); }

            /**
             * Creates a dependency provider for fragment (androidx.fragment:fragment)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getFragment() { return create("androidx.fragment"); }

            /**
             * Creates a dependency provider for interpolator (androidx.interpolator:interpolator)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getInterpolator() { return create("androidx.interpolator"); }

            /**
             * Creates a dependency provider for loader (androidx.loader:loader)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getLoader() { return create("androidx.loader"); }

            /**
             * Creates a dependency provider for localbroadcastmanager (androidx.localbroadcastmanager:localbroadcastmanager)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getLocalbroadcastmanager() { return create("androidx.localbroadcastmanager"); }

            /**
             * Creates a dependency provider for print (androidx.print:print)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getPrint() { return create("androidx.print"); }

            /**
             * Creates a dependency provider for profileinstaller (androidx.profileinstaller:profileinstaller)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getProfileinstaller() { return create("androidx.profileinstaller"); }

            /**
             * Creates a dependency provider for recyclerview (androidx.recyclerview:recyclerview)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getRecyclerview() { return create("androidx.recyclerview"); }

            /**
             * Creates a dependency provider for tracing (androidx.tracing:tracing)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getTracing() { return create("androidx.tracing"); }

            /**
             * Creates a dependency provider for transition (androidx.transition:transition)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getTransition() { return create("androidx.transition"); }

            /**
             * Creates a dependency provider for versionedparcelable (androidx.versionedparcelable:versionedparcelable)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getVersionedparcelable() { return create("androidx.versionedparcelable"); }

            /**
             * Creates a dependency provider for viewpager (androidx.viewpager:viewpager)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getViewpager() { return create("androidx.viewpager"); }

            /**
             * Creates a dependency provider for viewpager2 (androidx.viewpager2:viewpager2)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getViewpager2() { return create("androidx.viewpager2"); }

        /**
         * Returns the group of libraries at androidx.activity
         */
        public AndroidxActivityLibraryAccessors getActivity() { return laccForAndroidxActivityLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.annotation
         */
        public AndroidxAnnotationLibraryAccessors getAnnotation() { return laccForAndroidxAnnotationLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.appcompat
         */
        public AndroidxAppcompatLibraryAccessors getAppcompat() { return laccForAndroidxAppcompatLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.arch
         */
        public AndroidxArchLibraryAccessors getArch() { return laccForAndroidxArchLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.collection
         */
        public AndroidxCollectionLibraryAccessors getCollection() { return laccForAndroidxCollectionLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.compose
         */
        public AndroidxComposeLibraryAccessors getCompose() { return laccForAndroidxComposeLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.concurrent
         */
        public AndroidxConcurrentLibraryAccessors getConcurrent() { return laccForAndroidxConcurrentLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.constraintlayout
         */
        public AndroidxConstraintlayoutLibraryAccessors getConstraintlayout() { return laccForAndroidxConstraintlayoutLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.core
         */
        public AndroidxCoreLibraryAccessors getCore() { return laccForAndroidxCoreLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.customview
         */
        public AndroidxCustomviewLibraryAccessors getCustomview() { return laccForAndroidxCustomviewLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.emoji2
         */
        public AndroidxEmoji2LibraryAccessors getEmoji2() { return laccForAndroidxEmoji2LibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.legacy
         */
        public AndroidxLegacyLibraryAccessors getLegacy() { return laccForAndroidxLegacyLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.lifecycle
         */
        public AndroidxLifecycleLibraryAccessors getLifecycle() { return laccForAndroidxLifecycleLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.navigation
         */
        public AndroidxNavigationLibraryAccessors getNavigation() { return laccForAndroidxNavigationLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.resourceinspection
         */
        public AndroidxResourceinspectionLibraryAccessors getResourceinspection() { return laccForAndroidxResourceinspectionLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.savedstate
         */
        public AndroidxSavedstateLibraryAccessors getSavedstate() { return laccForAndroidxSavedstateLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.startup
         */
        public AndroidxStartupLibraryAccessors getStartup() { return laccForAndroidxStartupLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.test
         */
        public AndroidxTestLibraryAccessors getTest() { return laccForAndroidxTestLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.vectordrawable
         */
        public AndroidxVectordrawableLibraryAccessors getVectordrawable() { return laccForAndroidxVectordrawableLibraryAccessors; }

    }

    public static class AndroidxActivityLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {
        private final AndroidxActivityActivityLibraryAccessors laccForAndroidxActivityActivityLibraryAccessors = new AndroidxActivityActivityLibraryAccessors(owner);

        public AndroidxActivityLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for activity (androidx.activity:activity)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("androidx.activity"); }

        /**
         * Returns the group of libraries at androidx.activity.activity
         */
        public AndroidxActivityActivityLibraryAccessors getActivity() { return laccForAndroidxActivityActivityLibraryAccessors; }

    }

    public static class AndroidxActivityActivityLibraryAccessors extends SubDependencyFactory {

        public AndroidxActivityActivityLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for compose (androidx.activity:activity-compose)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCompose() { return create("androidx.activity.activity.compose"); }

            /**
             * Creates a dependency provider for ktx (androidx.activity:activity-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() { return create("androidx.activity.activity.ktx"); }

    }

    public static class AndroidxAnnotationLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {
        private final AndroidxAnnotationAnnotationLibraryAccessors laccForAndroidxAnnotationAnnotationLibraryAccessors = new AndroidxAnnotationAnnotationLibraryAccessors(owner);

        public AndroidxAnnotationLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for annotation (androidx.annotation:annotation)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("androidx.annotation"); }

        /**
         * Returns the group of libraries at androidx.annotation.annotation
         */
        public AndroidxAnnotationAnnotationLibraryAccessors getAnnotation() { return laccForAndroidxAnnotationAnnotationLibraryAccessors; }

    }

    public static class AndroidxAnnotationAnnotationLibraryAccessors extends SubDependencyFactory {

        public AndroidxAnnotationAnnotationLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for experimental (androidx.annotation:annotation-experimental)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getExperimental() { return create("androidx.annotation.annotation.experimental"); }

    }

    public static class AndroidxAppcompatLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {
        private final AndroidxAppcompatAppcompatLibraryAccessors laccForAndroidxAppcompatAppcompatLibraryAccessors = new AndroidxAppcompatAppcompatLibraryAccessors(owner);

        public AndroidxAppcompatLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for appcompat (androidx.appcompat:appcompat)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("androidx.appcompat"); }

        /**
         * Returns the group of libraries at androidx.appcompat.appcompat
         */
        public AndroidxAppcompatAppcompatLibraryAccessors getAppcompat() { return laccForAndroidxAppcompatAppcompatLibraryAccessors; }

    }

    public static class AndroidxAppcompatAppcompatLibraryAccessors extends SubDependencyFactory {

        public AndroidxAppcompatAppcompatLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for resources (androidx.appcompat:appcompat-resources)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getResources() { return create("androidx.appcompat.appcompat.resources"); }

    }

    public static class AndroidxArchLibraryAccessors extends SubDependencyFactory {
        private final AndroidxArchCoreLibraryAccessors laccForAndroidxArchCoreLibraryAccessors = new AndroidxArchCoreLibraryAccessors(owner);

        public AndroidxArchLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at androidx.arch.core
         */
        public AndroidxArchCoreLibraryAccessors getCore() { return laccForAndroidxArchCoreLibraryAccessors; }

    }

    public static class AndroidxArchCoreLibraryAccessors extends SubDependencyFactory {
        private final AndroidxArchCoreCoreLibraryAccessors laccForAndroidxArchCoreCoreLibraryAccessors = new AndroidxArchCoreCoreLibraryAccessors(owner);

        public AndroidxArchCoreLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at androidx.arch.core.core
         */
        public AndroidxArchCoreCoreLibraryAccessors getCore() { return laccForAndroidxArchCoreCoreLibraryAccessors; }

    }

    public static class AndroidxArchCoreCoreLibraryAccessors extends SubDependencyFactory {

        public AndroidxArchCoreCoreLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for common (androidx.arch.core:core-common)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCommon() { return create("androidx.arch.core.core.common"); }

            /**
             * Creates a dependency provider for runtime (androidx.arch.core:core-runtime)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getRuntime() { return create("androidx.arch.core.core.runtime"); }

    }

    public static class AndroidxCollectionLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {
        private final AndroidxCollectionCollectionLibraryAccessors laccForAndroidxCollectionCollectionLibraryAccessors = new AndroidxCollectionCollectionLibraryAccessors(owner);

        public AndroidxCollectionLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for collection (androidx.collection:collection)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("androidx.collection"); }

        /**
         * Returns the group of libraries at androidx.collection.collection
         */
        public AndroidxCollectionCollectionLibraryAccessors getCollection() { return laccForAndroidxCollectionCollectionLibraryAccessors; }

    }

    public static class AndroidxCollectionCollectionLibraryAccessors extends SubDependencyFactory {

        public AndroidxCollectionCollectionLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ktx (androidx.collection:collection-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() { return create("androidx.collection.collection.ktx"); }

    }

    public static class AndroidxComposeLibraryAccessors extends SubDependencyFactory {
        private final AndroidxComposeAnimationLibraryAccessors laccForAndroidxComposeAnimationLibraryAccessors = new AndroidxComposeAnimationLibraryAccessors(owner);
        private final AndroidxComposeFoundationLibraryAccessors laccForAndroidxComposeFoundationLibraryAccessors = new AndroidxComposeFoundationLibraryAccessors(owner);
        private final AndroidxComposeMaterialLibraryAccessors laccForAndroidxComposeMaterialLibraryAccessors = new AndroidxComposeMaterialLibraryAccessors(owner);
        private final AndroidxComposeRuntimeLibraryAccessors laccForAndroidxComposeRuntimeLibraryAccessors = new AndroidxComposeRuntimeLibraryAccessors(owner);
        private final AndroidxComposeUiLibraryAccessors laccForAndroidxComposeUiLibraryAccessors = new AndroidxComposeUiLibraryAccessors(owner);

        public AndroidxComposeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for compiler (androidx.compose.compiler:compiler)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCompiler() { return create("androidx.compose.compiler"); }

        /**
         * Returns the group of libraries at androidx.compose.animation
         */
        public AndroidxComposeAnimationLibraryAccessors getAnimation() { return laccForAndroidxComposeAnimationLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.compose.foundation
         */
        public AndroidxComposeFoundationLibraryAccessors getFoundation() { return laccForAndroidxComposeFoundationLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.compose.material
         */
        public AndroidxComposeMaterialLibraryAccessors getMaterial() { return laccForAndroidxComposeMaterialLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.compose.runtime
         */
        public AndroidxComposeRuntimeLibraryAccessors getRuntime() { return laccForAndroidxComposeRuntimeLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.compose.ui
         */
        public AndroidxComposeUiLibraryAccessors getUi() { return laccForAndroidxComposeUiLibraryAccessors; }

    }

    public static class AndroidxComposeAnimationLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {
        private final AndroidxComposeAnimationAnimationLibraryAccessors laccForAndroidxComposeAnimationAnimationLibraryAccessors = new AndroidxComposeAnimationAnimationLibraryAccessors(owner);

        public AndroidxComposeAnimationLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for animation (androidx.compose.animation:animation)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("androidx.compose.animation"); }

        /**
         * Returns the group of libraries at androidx.compose.animation.animation
         */
        public AndroidxComposeAnimationAnimationLibraryAccessors getAnimation() { return laccForAndroidxComposeAnimationAnimationLibraryAccessors; }

    }

    public static class AndroidxComposeAnimationAnimationLibraryAccessors extends SubDependencyFactory {

        public AndroidxComposeAnimationAnimationLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for core (androidx.compose.animation:animation-core)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCore() { return create("androidx.compose.animation.animation.core"); }

    }

    public static class AndroidxComposeFoundationLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {
        private final AndroidxComposeFoundationFoundationLibraryAccessors laccForAndroidxComposeFoundationFoundationLibraryAccessors = new AndroidxComposeFoundationFoundationLibraryAccessors(owner);

        public AndroidxComposeFoundationLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for foundation (androidx.compose.foundation:foundation)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("androidx.compose.foundation"); }

        /**
         * Returns the group of libraries at androidx.compose.foundation.foundation
         */
        public AndroidxComposeFoundationFoundationLibraryAccessors getFoundation() { return laccForAndroidxComposeFoundationFoundationLibraryAccessors; }

    }

    public static class AndroidxComposeFoundationFoundationLibraryAccessors extends SubDependencyFactory {

        public AndroidxComposeFoundationFoundationLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for layout (androidx.compose.foundation:foundation-layout)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getLayout() { return create("androidx.compose.foundation.foundation.layout"); }

    }

    public static class AndroidxComposeMaterialLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {
        private final AndroidxComposeMaterialMaterialLibraryAccessors laccForAndroidxComposeMaterialMaterialLibraryAccessors = new AndroidxComposeMaterialMaterialLibraryAccessors(owner);

        public AndroidxComposeMaterialLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for material (androidx.compose.material:material)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("androidx.compose.material"); }

        /**
         * Returns the group of libraries at androidx.compose.material.material
         */
        public AndroidxComposeMaterialMaterialLibraryAccessors getMaterial() { return laccForAndroidxComposeMaterialMaterialLibraryAccessors; }

    }

    public static class AndroidxComposeMaterialMaterialLibraryAccessors extends SubDependencyFactory {
        private final AndroidxComposeMaterialMaterialIconsLibraryAccessors laccForAndroidxComposeMaterialMaterialIconsLibraryAccessors = new AndroidxComposeMaterialMaterialIconsLibraryAccessors(owner);

        public AndroidxComposeMaterialMaterialLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ripple (androidx.compose.material:material-ripple)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getRipple() { return create("androidx.compose.material.material.ripple"); }

        /**
         * Returns the group of libraries at androidx.compose.material.material.icons
         */
        public AndroidxComposeMaterialMaterialIconsLibraryAccessors getIcons() { return laccForAndroidxComposeMaterialMaterialIconsLibraryAccessors; }

    }

    public static class AndroidxComposeMaterialMaterialIconsLibraryAccessors extends SubDependencyFactory {

        public AndroidxComposeMaterialMaterialIconsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for core (androidx.compose.material:material-icons-core)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCore() { return create("androidx.compose.material.material.icons.core"); }

            /**
             * Creates a dependency provider for extended (androidx.compose.material:material-icons-extended)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getExtended() { return create("androidx.compose.material.material.icons.extended"); }

    }

    public static class AndroidxComposeRuntimeLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {
        private final AndroidxComposeRuntimeRuntimeLibraryAccessors laccForAndroidxComposeRuntimeRuntimeLibraryAccessors = new AndroidxComposeRuntimeRuntimeLibraryAccessors(owner);

        public AndroidxComposeRuntimeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for runtime (androidx.compose.runtime:runtime)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("androidx.compose.runtime"); }

        /**
         * Returns the group of libraries at androidx.compose.runtime.runtime
         */
        public AndroidxComposeRuntimeRuntimeLibraryAccessors getRuntime() { return laccForAndroidxComposeRuntimeRuntimeLibraryAccessors; }

    }

    public static class AndroidxComposeRuntimeRuntimeLibraryAccessors extends SubDependencyFactory {

        public AndroidxComposeRuntimeRuntimeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for saveable (androidx.compose.runtime:runtime-saveable)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getSaveable() { return create("androidx.compose.runtime.runtime.saveable"); }

    }

    public static class AndroidxComposeUiLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {
        private final AndroidxComposeUiUiLibraryAccessors laccForAndroidxComposeUiUiLibraryAccessors = new AndroidxComposeUiUiLibraryAccessors(owner);

        public AndroidxComposeUiLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ui (androidx.compose.ui:ui)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("androidx.compose.ui"); }

        /**
         * Returns the group of libraries at androidx.compose.ui.ui
         */
        public AndroidxComposeUiUiLibraryAccessors getUi() { return laccForAndroidxComposeUiUiLibraryAccessors; }

    }

    public static class AndroidxComposeUiUiLibraryAccessors extends SubDependencyFactory {
        private final AndroidxComposeUiUiToolingLibraryAccessors laccForAndroidxComposeUiUiToolingLibraryAccessors = new AndroidxComposeUiUiToolingLibraryAccessors(owner);

        public AndroidxComposeUiUiLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for geometry (androidx.compose.ui:ui-geometry)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getGeometry() { return create("androidx.compose.ui.ui.geometry"); }

            /**
             * Creates a dependency provider for graphics (androidx.compose.ui:ui-graphics)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getGraphics() { return create("androidx.compose.ui.ui.graphics"); }

            /**
             * Creates a dependency provider for text (androidx.compose.ui:ui-text)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getText() { return create("androidx.compose.ui.ui.text"); }

            /**
             * Creates a dependency provider for unit (androidx.compose.ui:ui-unit)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getUnit() { return create("androidx.compose.ui.ui.unit"); }

            /**
             * Creates a dependency provider for util (androidx.compose.ui:ui-util)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getUtil() { return create("androidx.compose.ui.ui.util"); }

        /**
         * Returns the group of libraries at androidx.compose.ui.ui.tooling
         */
        public AndroidxComposeUiUiToolingLibraryAccessors getTooling() { return laccForAndroidxComposeUiUiToolingLibraryAccessors; }

    }

    public static class AndroidxComposeUiUiToolingLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public AndroidxComposeUiUiToolingLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for tooling (androidx.compose.ui:ui-tooling)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("androidx.compose.ui.ui.tooling"); }

            /**
             * Creates a dependency provider for data (androidx.compose.ui:ui-tooling-data)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getData() { return create("androidx.compose.ui.ui.tooling.data"); }

            /**
             * Creates a dependency provider for preview (androidx.compose.ui:ui-tooling-preview)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getPreview() { return create("androidx.compose.ui.ui.tooling.preview"); }

    }

    public static class AndroidxConcurrentLibraryAccessors extends SubDependencyFactory {
        private final AndroidxConcurrentConcurrentLibraryAccessors laccForAndroidxConcurrentConcurrentLibraryAccessors = new AndroidxConcurrentConcurrentLibraryAccessors(owner);

        public AndroidxConcurrentLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at androidx.concurrent.concurrent
         */
        public AndroidxConcurrentConcurrentLibraryAccessors getConcurrent() { return laccForAndroidxConcurrentConcurrentLibraryAccessors; }

    }

    public static class AndroidxConcurrentConcurrentLibraryAccessors extends SubDependencyFactory {

        public AndroidxConcurrentConcurrentLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for futures (androidx.concurrent:concurrent-futures)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getFutures() { return create("androidx.concurrent.concurrent.futures"); }

    }

    public static class AndroidxConstraintlayoutLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {
        private final AndroidxConstraintlayoutConstraintlayoutLibraryAccessors laccForAndroidxConstraintlayoutConstraintlayoutLibraryAccessors = new AndroidxConstraintlayoutConstraintlayoutLibraryAccessors(owner);

        public AndroidxConstraintlayoutLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for constraintlayout (androidx.constraintlayout:constraintlayout)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("androidx.constraintlayout"); }

        /**
         * Returns the group of libraries at androidx.constraintlayout.constraintlayout
         */
        public AndroidxConstraintlayoutConstraintlayoutLibraryAccessors getConstraintlayout() { return laccForAndroidxConstraintlayoutConstraintlayoutLibraryAccessors; }

    }

    public static class AndroidxConstraintlayoutConstraintlayoutLibraryAccessors extends SubDependencyFactory {

        public AndroidxConstraintlayoutConstraintlayoutLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for solver (androidx.constraintlayout:constraintlayout-solver)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getSolver() { return create("androidx.constraintlayout.constraintlayout.solver"); }

    }

    public static class AndroidxCoreLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {
        private final AndroidxCoreCoreLibraryAccessors laccForAndroidxCoreCoreLibraryAccessors = new AndroidxCoreCoreLibraryAccessors(owner);

        public AndroidxCoreLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for core (androidx.core:core)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("androidx.core"); }

        /**
         * Returns the group of libraries at androidx.core.core
         */
        public AndroidxCoreCoreLibraryAccessors getCore() { return laccForAndroidxCoreCoreLibraryAccessors; }

    }

    public static class AndroidxCoreCoreLibraryAccessors extends SubDependencyFactory {

        public AndroidxCoreCoreLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ktx (androidx.core:core-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() { return create("androidx.core.core.ktx"); }

    }

    public static class AndroidxCustomviewLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {
        private final AndroidxCustomviewCustomviewLibraryAccessors laccForAndroidxCustomviewCustomviewLibraryAccessors = new AndroidxCustomviewCustomviewLibraryAccessors(owner);

        public AndroidxCustomviewLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for customview (androidx.customview:customview)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("androidx.customview"); }

        /**
         * Returns the group of libraries at androidx.customview.customview
         */
        public AndroidxCustomviewCustomviewLibraryAccessors getCustomview() { return laccForAndroidxCustomviewCustomviewLibraryAccessors; }

    }

    public static class AndroidxCustomviewCustomviewLibraryAccessors extends SubDependencyFactory {

        public AndroidxCustomviewCustomviewLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for poolingcontainer (androidx.customview:customview-poolingcontainer)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getPoolingcontainer() { return create("androidx.customview.customview.poolingcontainer"); }

    }

    public static class AndroidxEmoji2LibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {
        private final AndroidxEmoji2Emoji2LibraryAccessors laccForAndroidxEmoji2Emoji2LibraryAccessors = new AndroidxEmoji2Emoji2LibraryAccessors(owner);

        public AndroidxEmoji2LibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for emoji2 (androidx.emoji2:emoji2)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("androidx.emoji2"); }

        /**
         * Returns the group of libraries at androidx.emoji2.emoji2
         */
        public AndroidxEmoji2Emoji2LibraryAccessors getEmoji2() { return laccForAndroidxEmoji2Emoji2LibraryAccessors; }

    }

    public static class AndroidxEmoji2Emoji2LibraryAccessors extends SubDependencyFactory {
        private final AndroidxEmoji2Emoji2ViewsLibraryAccessors laccForAndroidxEmoji2Emoji2ViewsLibraryAccessors = new AndroidxEmoji2Emoji2ViewsLibraryAccessors(owner);

        public AndroidxEmoji2Emoji2LibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at androidx.emoji2.emoji2.views
         */
        public AndroidxEmoji2Emoji2ViewsLibraryAccessors getViews() { return laccForAndroidxEmoji2Emoji2ViewsLibraryAccessors; }

    }

    public static class AndroidxEmoji2Emoji2ViewsLibraryAccessors extends SubDependencyFactory {

        public AndroidxEmoji2Emoji2ViewsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for helper (androidx.emoji2:emoji2-views-helper)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getHelper() { return create("androidx.emoji2.emoji2.views.helper"); }

    }

    public static class AndroidxLegacyLibraryAccessors extends SubDependencyFactory {
        private final AndroidxLegacyLegacyLibraryAccessors laccForAndroidxLegacyLegacyLibraryAccessors = new AndroidxLegacyLegacyLibraryAccessors(owner);

        public AndroidxLegacyLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at androidx.legacy.legacy
         */
        public AndroidxLegacyLegacyLibraryAccessors getLegacy() { return laccForAndroidxLegacyLegacyLibraryAccessors; }

    }

    public static class AndroidxLegacyLegacyLibraryAccessors extends SubDependencyFactory {
        private final AndroidxLegacyLegacySupportLibraryAccessors laccForAndroidxLegacyLegacySupportLibraryAccessors = new AndroidxLegacyLegacySupportLibraryAccessors(owner);

        public AndroidxLegacyLegacyLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at androidx.legacy.legacy.support
         */
        public AndroidxLegacyLegacySupportLibraryAccessors getSupport() { return laccForAndroidxLegacyLegacySupportLibraryAccessors; }

    }

    public static class AndroidxLegacyLegacySupportLibraryAccessors extends SubDependencyFactory {
        private final AndroidxLegacyLegacySupportCoreLibraryAccessors laccForAndroidxLegacyLegacySupportCoreLibraryAccessors = new AndroidxLegacyLegacySupportCoreLibraryAccessors(owner);

        public AndroidxLegacyLegacySupportLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at androidx.legacy.legacy.support.core
         */
        public AndroidxLegacyLegacySupportCoreLibraryAccessors getCore() { return laccForAndroidxLegacyLegacySupportCoreLibraryAccessors; }

    }

    public static class AndroidxLegacyLegacySupportCoreLibraryAccessors extends SubDependencyFactory {

        public AndroidxLegacyLegacySupportCoreLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for utils (androidx.legacy:legacy-support-core-utils)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getUtils() { return create("androidx.legacy.legacy.support.core.utils"); }

    }

    public static class AndroidxLifecycleLibraryAccessors extends SubDependencyFactory {
        private final AndroidxLifecycleLifecycleLibraryAccessors laccForAndroidxLifecycleLifecycleLibraryAccessors = new AndroidxLifecycleLifecycleLibraryAccessors(owner);

        public AndroidxLifecycleLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at androidx.lifecycle.lifecycle
         */
        public AndroidxLifecycleLifecycleLibraryAccessors getLifecycle() { return laccForAndroidxLifecycleLifecycleLibraryAccessors; }

    }

    public static class AndroidxLifecycleLifecycleLibraryAccessors extends SubDependencyFactory {
        private final AndroidxLifecycleLifecycleCommonLibraryAccessors laccForAndroidxLifecycleLifecycleCommonLibraryAccessors = new AndroidxLifecycleLifecycleCommonLibraryAccessors(owner);
        private final AndroidxLifecycleLifecycleLivedataLibraryAccessors laccForAndroidxLifecycleLifecycleLivedataLibraryAccessors = new AndroidxLifecycleLifecycleLivedataLibraryAccessors(owner);
        private final AndroidxLifecycleLifecycleRuntimeLibraryAccessors laccForAndroidxLifecycleLifecycleRuntimeLibraryAccessors = new AndroidxLifecycleLifecycleRuntimeLibraryAccessors(owner);
        private final AndroidxLifecycleLifecycleViewmodelLibraryAccessors laccForAndroidxLifecycleLifecycleViewmodelLibraryAccessors = new AndroidxLifecycleLifecycleViewmodelLibraryAccessors(owner);

        public AndroidxLifecycleLifecycleLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for process (androidx.lifecycle:lifecycle-process)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getProcess() { return create("androidx.lifecycle.lifecycle.process"); }

        /**
         * Returns the group of libraries at androidx.lifecycle.lifecycle.common
         */
        public AndroidxLifecycleLifecycleCommonLibraryAccessors getCommon() { return laccForAndroidxLifecycleLifecycleCommonLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.lifecycle.lifecycle.livedata
         */
        public AndroidxLifecycleLifecycleLivedataLibraryAccessors getLivedata() { return laccForAndroidxLifecycleLifecycleLivedataLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.lifecycle.lifecycle.runtime
         */
        public AndroidxLifecycleLifecycleRuntimeLibraryAccessors getRuntime() { return laccForAndroidxLifecycleLifecycleRuntimeLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.lifecycle.lifecycle.viewmodel
         */
        public AndroidxLifecycleLifecycleViewmodelLibraryAccessors getViewmodel() { return laccForAndroidxLifecycleLifecycleViewmodelLibraryAccessors; }

    }

    public static class AndroidxLifecycleLifecycleCommonLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public AndroidxLifecycleLifecycleCommonLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for common (androidx.lifecycle:lifecycle-common)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("androidx.lifecycle.lifecycle.common"); }

            /**
             * Creates a dependency provider for java8 (androidx.lifecycle:lifecycle-common-java8)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getJava8() { return create("androidx.lifecycle.lifecycle.common.java8"); }

    }

    public static class AndroidxLifecycleLifecycleLivedataLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public AndroidxLifecycleLifecycleLivedataLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for livedata (androidx.lifecycle:lifecycle-livedata)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("androidx.lifecycle.lifecycle.livedata"); }

            /**
             * Creates a dependency provider for core (androidx.lifecycle:lifecycle-livedata-core)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCore() { return create("androidx.lifecycle.lifecycle.livedata.core"); }

    }

    public static class AndroidxLifecycleLifecycleRuntimeLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public AndroidxLifecycleLifecycleRuntimeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for runtime (androidx.lifecycle:lifecycle-runtime)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("androidx.lifecycle.lifecycle.runtime"); }

            /**
             * Creates a dependency provider for ktx (androidx.lifecycle:lifecycle-runtime-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() { return create("androidx.lifecycle.lifecycle.runtime.ktx"); }

    }

    public static class AndroidxLifecycleLifecycleViewmodelLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public AndroidxLifecycleLifecycleViewmodelLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for viewmodel (androidx.lifecycle:lifecycle-viewmodel)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("androidx.lifecycle.lifecycle.viewmodel"); }

            /**
             * Creates a dependency provider for compose (androidx.lifecycle:lifecycle-viewmodel-compose)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCompose() { return create("androidx.lifecycle.lifecycle.viewmodel.compose"); }

            /**
             * Creates a dependency provider for ktx (androidx.lifecycle:lifecycle-viewmodel-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() { return create("androidx.lifecycle.lifecycle.viewmodel.ktx"); }

            /**
             * Creates a dependency provider for savedstate (androidx.lifecycle:lifecycle-viewmodel-savedstate)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getSavedstate() { return create("androidx.lifecycle.lifecycle.viewmodel.savedstate"); }

    }

    public static class AndroidxNavigationLibraryAccessors extends SubDependencyFactory {
        private final AndroidxNavigationNavigationLibraryAccessors laccForAndroidxNavigationNavigationLibraryAccessors = new AndroidxNavigationNavigationLibraryAccessors(owner);

        public AndroidxNavigationLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at androidx.navigation.navigation
         */
        public AndroidxNavigationNavigationLibraryAccessors getNavigation() { return laccForAndroidxNavigationNavigationLibraryAccessors; }

    }

    public static class AndroidxNavigationNavigationLibraryAccessors extends SubDependencyFactory {
        private final AndroidxNavigationNavigationCommonLibraryAccessors laccForAndroidxNavigationNavigationCommonLibraryAccessors = new AndroidxNavigationNavigationCommonLibraryAccessors(owner);
        private final AndroidxNavigationNavigationRuntimeLibraryAccessors laccForAndroidxNavigationNavigationRuntimeLibraryAccessors = new AndroidxNavigationNavigationRuntimeLibraryAccessors(owner);

        public AndroidxNavigationNavigationLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for compose (androidx.navigation:navigation-compose)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCompose() { return create("androidx.navigation.navigation.compose"); }

        /**
         * Returns the group of libraries at androidx.navigation.navigation.common
         */
        public AndroidxNavigationNavigationCommonLibraryAccessors getCommon() { return laccForAndroidxNavigationNavigationCommonLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.navigation.navigation.runtime
         */
        public AndroidxNavigationNavigationRuntimeLibraryAccessors getRuntime() { return laccForAndroidxNavigationNavigationRuntimeLibraryAccessors; }

    }

    public static class AndroidxNavigationNavigationCommonLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public AndroidxNavigationNavigationCommonLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for common (androidx.navigation:navigation-common)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("androidx.navigation.navigation.common"); }

            /**
             * Creates a dependency provider for ktx (androidx.navigation:navigation-common-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() { return create("androidx.navigation.navigation.common.ktx"); }

    }

    public static class AndroidxNavigationNavigationRuntimeLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public AndroidxNavigationNavigationRuntimeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for runtime (androidx.navigation:navigation-runtime)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("androidx.navigation.navigation.runtime"); }

            /**
             * Creates a dependency provider for ktx (androidx.navigation:navigation-runtime-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() { return create("androidx.navigation.navigation.runtime.ktx"); }

    }

    public static class AndroidxResourceinspectionLibraryAccessors extends SubDependencyFactory {
        private final AndroidxResourceinspectionResourceinspectionLibraryAccessors laccForAndroidxResourceinspectionResourceinspectionLibraryAccessors = new AndroidxResourceinspectionResourceinspectionLibraryAccessors(owner);

        public AndroidxResourceinspectionLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at androidx.resourceinspection.resourceinspection
         */
        public AndroidxResourceinspectionResourceinspectionLibraryAccessors getResourceinspection() { return laccForAndroidxResourceinspectionResourceinspectionLibraryAccessors; }

    }

    public static class AndroidxResourceinspectionResourceinspectionLibraryAccessors extends SubDependencyFactory {

        public AndroidxResourceinspectionResourceinspectionLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for annotation (androidx.resourceinspection:resourceinspection-annotation)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getAnnotation() { return create("androidx.resourceinspection.resourceinspection.annotation"); }

    }

    public static class AndroidxSavedstateLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {
        private final AndroidxSavedstateSavedstateLibraryAccessors laccForAndroidxSavedstateSavedstateLibraryAccessors = new AndroidxSavedstateSavedstateLibraryAccessors(owner);

        public AndroidxSavedstateLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for savedstate (androidx.savedstate:savedstate)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("androidx.savedstate"); }

        /**
         * Returns the group of libraries at androidx.savedstate.savedstate
         */
        public AndroidxSavedstateSavedstateLibraryAccessors getSavedstate() { return laccForAndroidxSavedstateSavedstateLibraryAccessors; }

    }

    public static class AndroidxSavedstateSavedstateLibraryAccessors extends SubDependencyFactory {

        public AndroidxSavedstateSavedstateLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ktx (androidx.savedstate:savedstate-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() { return create("androidx.savedstate.savedstate.ktx"); }

    }

    public static class AndroidxStartupLibraryAccessors extends SubDependencyFactory {
        private final AndroidxStartupStartupLibraryAccessors laccForAndroidxStartupStartupLibraryAccessors = new AndroidxStartupStartupLibraryAccessors(owner);

        public AndroidxStartupLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at androidx.startup.startup
         */
        public AndroidxStartupStartupLibraryAccessors getStartup() { return laccForAndroidxStartupStartupLibraryAccessors; }

    }

    public static class AndroidxStartupStartupLibraryAccessors extends SubDependencyFactory {

        public AndroidxStartupStartupLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for runtime (androidx.startup:startup-runtime)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getRuntime() { return create("androidx.startup.startup.runtime"); }

    }

    public static class AndroidxTestLibraryAccessors extends SubDependencyFactory {
        private final AndroidxTestEspressoLibraryAccessors laccForAndroidxTestEspressoLibraryAccessors = new AndroidxTestEspressoLibraryAccessors(owner);
        private final AndroidxTestExtLibraryAccessors laccForAndroidxTestExtLibraryAccessors = new AndroidxTestExtLibraryAccessors(owner);

        public AndroidxTestLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at androidx.test.espresso
         */
        public AndroidxTestEspressoLibraryAccessors getEspresso() { return laccForAndroidxTestEspressoLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.test.ext
         */
        public AndroidxTestExtLibraryAccessors getExt() { return laccForAndroidxTestExtLibraryAccessors; }

    }

    public static class AndroidxTestEspressoLibraryAccessors extends SubDependencyFactory {
        private final AndroidxTestEspressoEspressoLibraryAccessors laccForAndroidxTestEspressoEspressoLibraryAccessors = new AndroidxTestEspressoEspressoLibraryAccessors(owner);

        public AndroidxTestEspressoLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at androidx.test.espresso.espresso
         */
        public AndroidxTestEspressoEspressoLibraryAccessors getEspresso() { return laccForAndroidxTestEspressoEspressoLibraryAccessors; }

    }

    public static class AndroidxTestEspressoEspressoLibraryAccessors extends SubDependencyFactory {

        public AndroidxTestEspressoEspressoLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for core (androidx.test.espresso:espresso-core)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCore() { return create("androidx.test.espresso.espresso.core"); }

    }

    public static class AndroidxTestExtLibraryAccessors extends SubDependencyFactory {

        public AndroidxTestExtLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for junit (androidx.test.ext:junit)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getJunit() { return create("androidx.test.ext.junit"); }

    }

    public static class AndroidxVectordrawableLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {
        private final AndroidxVectordrawableVectordrawableLibraryAccessors laccForAndroidxVectordrawableVectordrawableLibraryAccessors = new AndroidxVectordrawableVectordrawableLibraryAccessors(owner);

        public AndroidxVectordrawableLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for vectordrawable (androidx.vectordrawable:vectordrawable)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("androidx.vectordrawable"); }

        /**
         * Returns the group of libraries at androidx.vectordrawable.vectordrawable
         */
        public AndroidxVectordrawableVectordrawableLibraryAccessors getVectordrawable() { return laccForAndroidxVectordrawableVectordrawableLibraryAccessors; }

    }

    public static class AndroidxVectordrawableVectordrawableLibraryAccessors extends SubDependencyFactory {

        public AndroidxVectordrawableVectordrawableLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for animated (androidx.vectordrawable:vectordrawable-animated)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getAnimated() { return create("androidx.vectordrawable.vectordrawable.animated"); }

    }

    public static class ComLibraryAccessors extends SubDependencyFactory {
        private final ComGoogleLibraryAccessors laccForComGoogleLibraryAccessors = new ComGoogleLibraryAccessors(owner);

        public ComLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at com.google
         */
        public ComGoogleLibraryAccessors getGoogle() { return laccForComGoogleLibraryAccessors; }

    }

    public static class ComGoogleLibraryAccessors extends SubDependencyFactory {
        private final ComGoogleAndroidLibraryAccessors laccForComGoogleAndroidLibraryAccessors = new ComGoogleAndroidLibraryAccessors(owner);

        public ComGoogleLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at com.google.android
         */
        public ComGoogleAndroidLibraryAccessors getAndroid() { return laccForComGoogleAndroidLibraryAccessors; }

    }

    public static class ComGoogleAndroidLibraryAccessors extends SubDependencyFactory {

        public ComGoogleAndroidLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for material (com.google.android.material:material)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getMaterial() { return create("com.google.android.material"); }

    }

    public static class OrgLibraryAccessors extends SubDependencyFactory {
        private final OrgApacheLibraryAccessors laccForOrgApacheLibraryAccessors = new OrgApacheLibraryAccessors(owner);
        private final OrgJacocoLibraryAccessors laccForOrgJacocoLibraryAccessors = new OrgJacocoLibraryAccessors(owner);
        private final OrgJetbrainsLibraryAccessors laccForOrgJetbrainsLibraryAccessors = new OrgJetbrainsLibraryAccessors(owner);

        public OrgLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.apache
         */
        public OrgApacheLibraryAccessors getApache() { return laccForOrgApacheLibraryAccessors; }

        /**
         * Returns the group of libraries at org.jacoco
         */
        public OrgJacocoLibraryAccessors getJacoco() { return laccForOrgJacocoLibraryAccessors; }

        /**
         * Returns the group of libraries at org.jetbrains
         */
        public OrgJetbrainsLibraryAccessors getJetbrains() { return laccForOrgJetbrainsLibraryAccessors; }

    }

    public static class OrgApacheLibraryAccessors extends SubDependencyFactory {
        private final OrgApacheLoggingLibraryAccessors laccForOrgApacheLoggingLibraryAccessors = new OrgApacheLoggingLibraryAccessors(owner);

        public OrgApacheLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.apache.logging
         */
        public OrgApacheLoggingLibraryAccessors getLogging() { return laccForOrgApacheLoggingLibraryAccessors; }

    }

    public static class OrgApacheLoggingLibraryAccessors extends SubDependencyFactory {
        private final OrgApacheLoggingLog4jLibraryAccessors laccForOrgApacheLoggingLog4jLibraryAccessors = new OrgApacheLoggingLog4jLibraryAccessors(owner);

        public OrgApacheLoggingLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.apache.logging.log4j
         */
        public OrgApacheLoggingLog4jLibraryAccessors getLog4j() { return laccForOrgApacheLoggingLog4jLibraryAccessors; }

    }

    public static class OrgApacheLoggingLog4jLibraryAccessors extends SubDependencyFactory {
        private final OrgApacheLoggingLog4jLog4jLibraryAccessors laccForOrgApacheLoggingLog4jLog4jLibraryAccessors = new OrgApacheLoggingLog4jLog4jLibraryAccessors(owner);

        public OrgApacheLoggingLog4jLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.apache.logging.log4j.log4j
         */
        public OrgApacheLoggingLog4jLog4jLibraryAccessors getLog4j() { return laccForOrgApacheLoggingLog4jLog4jLibraryAccessors; }

    }

    public static class OrgApacheLoggingLog4jLog4jLibraryAccessors extends SubDependencyFactory {

        public OrgApacheLoggingLog4jLog4jLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for core (org.apache.logging.log4j:log4j-core)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCore() { return create("org.apache.logging.log4j.log4j.core"); }

    }

    public static class OrgJacocoLibraryAccessors extends SubDependencyFactory {
        private final OrgJacocoOrgLibraryAccessors laccForOrgJacocoOrgLibraryAccessors = new OrgJacocoOrgLibraryAccessors(owner);

        public OrgJacocoLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.jacoco.org
         */
        public OrgJacocoOrgLibraryAccessors getOrg() { return laccForOrgJacocoOrgLibraryAccessors; }

    }

    public static class OrgJacocoOrgLibraryAccessors extends SubDependencyFactory {
        private final OrgJacocoOrgJacocoLibraryAccessors laccForOrgJacocoOrgJacocoLibraryAccessors = new OrgJacocoOrgJacocoLibraryAccessors(owner);

        public OrgJacocoOrgLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.jacoco.org.jacoco
         */
        public OrgJacocoOrgJacocoLibraryAccessors getJacoco() { return laccForOrgJacocoOrgJacocoLibraryAccessors; }

    }

    public static class OrgJacocoOrgJacocoLibraryAccessors extends SubDependencyFactory {

        public OrgJacocoOrgJacocoLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ant (org.jacoco:org.jacoco.ant)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getAnt() { return create("org.jacoco.org.jacoco.ant"); }

    }

    public static class OrgJetbrainsLibraryAccessors extends SubDependencyFactory {
        private final OrgJetbrainsKotlinLibraryAccessors laccForOrgJetbrainsKotlinLibraryAccessors = new OrgJetbrainsKotlinLibraryAccessors(owner);
        private final OrgJetbrainsKotlinxLibraryAccessors laccForOrgJetbrainsKotlinxLibraryAccessors = new OrgJetbrainsKotlinxLibraryAccessors(owner);

        public OrgJetbrainsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for annotations (org.jetbrains:annotations)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getAnnotations() { return create("org.jetbrains.annotations"); }

        /**
         * Returns the group of libraries at org.jetbrains.kotlin
         */
        public OrgJetbrainsKotlinLibraryAccessors getKotlin() { return laccForOrgJetbrainsKotlinLibraryAccessors; }

        /**
         * Returns the group of libraries at org.jetbrains.kotlinx
         */
        public OrgJetbrainsKotlinxLibraryAccessors getKotlinx() { return laccForOrgJetbrainsKotlinxLibraryAccessors; }

    }

    public static class OrgJetbrainsKotlinLibraryAccessors extends SubDependencyFactory {
        private final OrgJetbrainsKotlinKotlinLibraryAccessors laccForOrgJetbrainsKotlinKotlinLibraryAccessors = new OrgJetbrainsKotlinKotlinLibraryAccessors(owner);

        public OrgJetbrainsKotlinLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.jetbrains.kotlin.kotlin
         */
        public OrgJetbrainsKotlinKotlinLibraryAccessors getKotlin() { return laccForOrgJetbrainsKotlinKotlinLibraryAccessors; }

    }

    public static class OrgJetbrainsKotlinKotlinLibraryAccessors extends SubDependencyFactory {
        private final OrgJetbrainsKotlinKotlinAndroidLibraryAccessors laccForOrgJetbrainsKotlinKotlinAndroidLibraryAccessors = new OrgJetbrainsKotlinKotlinAndroidLibraryAccessors(owner);
        private final OrgJetbrainsKotlinKotlinAnnotationLibraryAccessors laccForOrgJetbrainsKotlinKotlinAnnotationLibraryAccessors = new OrgJetbrainsKotlinKotlinAnnotationLibraryAccessors(owner);
        private final OrgJetbrainsKotlinKotlinGradleLibraryAccessors laccForOrgJetbrainsKotlinKotlinGradleLibraryAccessors = new OrgJetbrainsKotlinKotlinGradleLibraryAccessors(owner);
        private final OrgJetbrainsKotlinKotlinParcelizeLibraryAccessors laccForOrgJetbrainsKotlinKotlinParcelizeLibraryAccessors = new OrgJetbrainsKotlinKotlinParcelizeLibraryAccessors(owner);
        private final OrgJetbrainsKotlinKotlinScriptingLibraryAccessors laccForOrgJetbrainsKotlinKotlinScriptingLibraryAccessors = new OrgJetbrainsKotlinKotlinScriptingLibraryAccessors(owner);
        private final OrgJetbrainsKotlinKotlinStdlibLibraryAccessors laccForOrgJetbrainsKotlinKotlinStdlibLibraryAccessors = new OrgJetbrainsKotlinKotlinStdlibLibraryAccessors(owner);

        public OrgJetbrainsKotlinKotlinLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for reflect (org.jetbrains.kotlin:kotlin-reflect)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getReflect() { return create("org.jetbrains.kotlin.kotlin.reflect"); }

        /**
         * Returns the group of libraries at org.jetbrains.kotlin.kotlin.android
         */
        public OrgJetbrainsKotlinKotlinAndroidLibraryAccessors getAndroid() { return laccForOrgJetbrainsKotlinKotlinAndroidLibraryAccessors; }

        /**
         * Returns the group of libraries at org.jetbrains.kotlin.kotlin.annotation
         */
        public OrgJetbrainsKotlinKotlinAnnotationLibraryAccessors getAnnotation() { return laccForOrgJetbrainsKotlinKotlinAnnotationLibraryAccessors; }

        /**
         * Returns the group of libraries at org.jetbrains.kotlin.kotlin.gradle
         */
        public OrgJetbrainsKotlinKotlinGradleLibraryAccessors getGradle() { return laccForOrgJetbrainsKotlinKotlinGradleLibraryAccessors; }

        /**
         * Returns the group of libraries at org.jetbrains.kotlin.kotlin.parcelize
         */
        public OrgJetbrainsKotlinKotlinParcelizeLibraryAccessors getParcelize() { return laccForOrgJetbrainsKotlinKotlinParcelizeLibraryAccessors; }

        /**
         * Returns the group of libraries at org.jetbrains.kotlin.kotlin.scripting
         */
        public OrgJetbrainsKotlinKotlinScriptingLibraryAccessors getScripting() { return laccForOrgJetbrainsKotlinKotlinScriptingLibraryAccessors; }

        /**
         * Returns the group of libraries at org.jetbrains.kotlin.kotlin.stdlib
         */
        public OrgJetbrainsKotlinKotlinStdlibLibraryAccessors getStdlib() { return laccForOrgJetbrainsKotlinKotlinStdlibLibraryAccessors; }

    }

    public static class OrgJetbrainsKotlinKotlinAndroidLibraryAccessors extends SubDependencyFactory {
        private final OrgJetbrainsKotlinKotlinAndroidExtensionsLibraryAccessors laccForOrgJetbrainsKotlinKotlinAndroidExtensionsLibraryAccessors = new OrgJetbrainsKotlinKotlinAndroidExtensionsLibraryAccessors(owner);

        public OrgJetbrainsKotlinKotlinAndroidLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.jetbrains.kotlin.kotlin.android.extensions
         */
        public OrgJetbrainsKotlinKotlinAndroidExtensionsLibraryAccessors getExtensions() { return laccForOrgJetbrainsKotlinKotlinAndroidExtensionsLibraryAccessors; }

    }

    public static class OrgJetbrainsKotlinKotlinAndroidExtensionsLibraryAccessors extends SubDependencyFactory {

        public OrgJetbrainsKotlinKotlinAndroidExtensionsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for runtime (org.jetbrains.kotlin:kotlin-android-extensions-runtime)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getRuntime() { return create("org.jetbrains.kotlin.kotlin.android.extensions.runtime"); }

    }

    public static class OrgJetbrainsKotlinKotlinAnnotationLibraryAccessors extends SubDependencyFactory {
        private final OrgJetbrainsKotlinKotlinAnnotationProcessingLibraryAccessors laccForOrgJetbrainsKotlinKotlinAnnotationProcessingLibraryAccessors = new OrgJetbrainsKotlinKotlinAnnotationProcessingLibraryAccessors(owner);

        public OrgJetbrainsKotlinKotlinAnnotationLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.jetbrains.kotlin.kotlin.annotation.processing
         */
        public OrgJetbrainsKotlinKotlinAnnotationProcessingLibraryAccessors getProcessing() { return laccForOrgJetbrainsKotlinKotlinAnnotationProcessingLibraryAccessors; }

    }

    public static class OrgJetbrainsKotlinKotlinAnnotationProcessingLibraryAccessors extends SubDependencyFactory {

        public OrgJetbrainsKotlinKotlinAnnotationProcessingLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for gradle (org.jetbrains.kotlin:kotlin-annotation-processing-gradle)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getGradle() { return create("org.jetbrains.kotlin.kotlin.annotation.processing.gradle"); }

    }

    public static class OrgJetbrainsKotlinKotlinGradleLibraryAccessors extends SubDependencyFactory {

        public OrgJetbrainsKotlinKotlinGradleLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for plugin (org.jetbrains.kotlin:kotlin-gradle-plugin)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getPlugin() { return create("org.jetbrains.kotlin.kotlin.gradle.plugin"); }

    }

    public static class OrgJetbrainsKotlinKotlinParcelizeLibraryAccessors extends SubDependencyFactory {

        public OrgJetbrainsKotlinKotlinParcelizeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for compiler (org.jetbrains.kotlin:kotlin-parcelize-compiler)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCompiler() { return create("org.jetbrains.kotlin.kotlin.parcelize.compiler"); }

            /**
             * Creates a dependency provider for runtime (org.jetbrains.kotlin:kotlin-parcelize-runtime)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getRuntime() { return create("org.jetbrains.kotlin.kotlin.parcelize.runtime"); }

    }

    public static class OrgJetbrainsKotlinKotlinScriptingLibraryAccessors extends SubDependencyFactory {
        private final OrgJetbrainsKotlinKotlinScriptingCompilerLibraryAccessors laccForOrgJetbrainsKotlinKotlinScriptingCompilerLibraryAccessors = new OrgJetbrainsKotlinKotlinScriptingCompilerLibraryAccessors(owner);

        public OrgJetbrainsKotlinKotlinScriptingLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.jetbrains.kotlin.kotlin.scripting.compiler
         */
        public OrgJetbrainsKotlinKotlinScriptingCompilerLibraryAccessors getCompiler() { return laccForOrgJetbrainsKotlinKotlinScriptingCompilerLibraryAccessors; }

    }

    public static class OrgJetbrainsKotlinKotlinScriptingCompilerLibraryAccessors extends SubDependencyFactory {

        public OrgJetbrainsKotlinKotlinScriptingCompilerLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for embeddable (org.jetbrains.kotlin:kotlin-scripting-compiler-embeddable)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getEmbeddable() { return create("org.jetbrains.kotlin.kotlin.scripting.compiler.embeddable"); }

    }

    public static class OrgJetbrainsKotlinKotlinStdlibLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public OrgJetbrainsKotlinKotlinStdlibLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for stdlib (org.jetbrains.kotlin:kotlin-stdlib)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("org.jetbrains.kotlin.kotlin.stdlib"); }

            /**
             * Creates a dependency provider for common (org.jetbrains.kotlin:kotlin-stdlib-common)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCommon() { return create("org.jetbrains.kotlin.kotlin.stdlib.common"); }

            /**
             * Creates a dependency provider for jdk7 (org.jetbrains.kotlin:kotlin-stdlib-jdk7)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getJdk7() { return create("org.jetbrains.kotlin.kotlin.stdlib.jdk7"); }

            /**
             * Creates a dependency provider for jdk8 (org.jetbrains.kotlin:kotlin-stdlib-jdk8)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getJdk8() { return create("org.jetbrains.kotlin.kotlin.stdlib.jdk8"); }

    }

    public static class OrgJetbrainsKotlinxLibraryAccessors extends SubDependencyFactory {
        private final OrgJetbrainsKotlinxKotlinxLibraryAccessors laccForOrgJetbrainsKotlinxKotlinxLibraryAccessors = new OrgJetbrainsKotlinxKotlinxLibraryAccessors(owner);

        public OrgJetbrainsKotlinxLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.jetbrains.kotlinx.kotlinx
         */
        public OrgJetbrainsKotlinxKotlinxLibraryAccessors getKotlinx() { return laccForOrgJetbrainsKotlinxKotlinxLibraryAccessors; }

    }

    public static class OrgJetbrainsKotlinxKotlinxLibraryAccessors extends SubDependencyFactory {
        private final OrgJetbrainsKotlinxKotlinxCoroutinesLibraryAccessors laccForOrgJetbrainsKotlinxKotlinxCoroutinesLibraryAccessors = new OrgJetbrainsKotlinxKotlinxCoroutinesLibraryAccessors(owner);

        public OrgJetbrainsKotlinxKotlinxLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at org.jetbrains.kotlinx.kotlinx.coroutines
         */
        public OrgJetbrainsKotlinxKotlinxCoroutinesLibraryAccessors getCoroutines() { return laccForOrgJetbrainsKotlinxKotlinxCoroutinesLibraryAccessors; }

    }

    public static class OrgJetbrainsKotlinxKotlinxCoroutinesLibraryAccessors extends SubDependencyFactory {
        private final OrgJetbrainsKotlinxKotlinxCoroutinesCoreLibraryAccessors laccForOrgJetbrainsKotlinxKotlinxCoroutinesCoreLibraryAccessors = new OrgJetbrainsKotlinxKotlinxCoroutinesCoreLibraryAccessors(owner);

        public OrgJetbrainsKotlinxKotlinxCoroutinesLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for android (org.jetbrains.kotlinx:kotlinx-coroutines-android)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getAndroid() { return create("org.jetbrains.kotlinx.kotlinx.coroutines.android"); }

            /**
             * Creates a dependency provider for bom (org.jetbrains.kotlinx:kotlinx-coroutines-bom)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getBom() { return create("org.jetbrains.kotlinx.kotlinx.coroutines.bom"); }

        /**
         * Returns the group of libraries at org.jetbrains.kotlinx.kotlinx.coroutines.core
         */
        public OrgJetbrainsKotlinxKotlinxCoroutinesCoreLibraryAccessors getCore() { return laccForOrgJetbrainsKotlinxKotlinxCoroutinesCoreLibraryAccessors; }

    }

    public static class OrgJetbrainsKotlinxKotlinxCoroutinesCoreLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public OrgJetbrainsKotlinxKotlinxCoroutinesCoreLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for core (org.jetbrains.kotlinx:kotlinx-coroutines-core)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("org.jetbrains.kotlinx.kotlinx.coroutines.core"); }

            /**
             * Creates a dependency provider for jvm (org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getJvm() { return create("org.jetbrains.kotlinx.kotlinx.coroutines.core.jvm"); }

    }

    public static class VersionAccessors extends VersionFactory  {

        private final AndroidxVersionAccessors vaccForAndroidxVersionAccessors = new AndroidxVersionAccessors(providers, config);
        private final OrgVersionAccessors vaccForOrgVersionAccessors = new OrgVersionAccessors(providers, config);
        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.androidx
         */
        public AndroidxVersionAccessors getAndroidx() { return vaccForAndroidxVersionAccessors; }

        /**
         * Returns the group of versions at versions.org
         */
        public OrgVersionAccessors getOrg() { return vaccForOrgVersionAccessors; }

    }

    public static class AndroidxVersionAccessors extends VersionFactory  {

        private final AndroidxArchVersionAccessors vaccForAndroidxArchVersionAccessors = new AndroidxArchVersionAccessors(providers, config);
        private final AndroidxComposeVersionAccessors vaccForAndroidxComposeVersionAccessors = new AndroidxComposeVersionAccessors(providers, config);
        public AndroidxVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: androidx.appcompat (1.5.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAppcompat() { return getVersion("androidx.appcompat"); }

            /**
             * Returns the version associated to this alias: androidx.collection (1.1.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getCollection() { return getVersion("androidx.collection"); }

            /**
             * Returns the version associated to this alias: androidx.constraintlayout (2.0.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getConstraintlayout() { return getVersion("androidx.constraintlayout"); }

            /**
             * Returns the version associated to this alias: androidx.emoji2 (1.2.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getEmoji2() { return getVersion("androidx.emoji2"); }

            /**
             * Returns the version associated to this alias: androidx.navigation (2.5.0-beta01)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getNavigation() { return getVersion("androidx.navigation"); }

            /**
             * Returns the version associated to this alias: androidx.savedstate (1.2.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getSavedstate() { return getVersion("androidx.savedstate"); }

            /**
             * Returns the version associated to this alias: androidx.vectordrawable (1.1.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getVectordrawable() { return getVersion("androidx.vectordrawable"); }

        /**
         * Returns the group of versions at versions.androidx.arch
         */
        public AndroidxArchVersionAccessors getArch() { return vaccForAndroidxArchVersionAccessors; }

        /**
         * Returns the group of versions at versions.androidx.compose
         */
        public AndroidxComposeVersionAccessors getCompose() { return vaccForAndroidxComposeVersionAccessors; }

    }

    public static class AndroidxArchVersionAccessors extends VersionFactory  {

        public AndroidxArchVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: androidx.arch.core (2.1.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getCore() { return getVersion("androidx.arch.core"); }

    }

    public static class AndroidxComposeVersionAccessors extends VersionFactory  {

        public AndroidxComposeVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: androidx.compose.animation (1.1.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAnimation() { return getVersion("androidx.compose.animation"); }

            /**
             * Returns the version associated to this alias: androidx.compose.foundation (1.2.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getFoundation() { return getVersion("androidx.compose.foundation"); }

            /**
             * Returns the version associated to this alias: androidx.compose.material (1.2.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getMaterial() { return getVersion("androidx.compose.material"); }

            /**
             * Returns the version associated to this alias: androidx.compose.runtime (1.2.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getRuntime() { return getVersion("androidx.compose.runtime"); }

            /**
             * Returns the version associated to this alias: androidx.compose.ui (1.2.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getUi() { return getVersion("androidx.compose.ui"); }

    }

    public static class OrgVersionAccessors extends VersionFactory  {

        private final OrgJetbrainsVersionAccessors vaccForOrgJetbrainsVersionAccessors = new OrgJetbrainsVersionAccessors(providers, config);
        public OrgVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.org.jetbrains
         */
        public OrgJetbrainsVersionAccessors getJetbrains() { return vaccForOrgJetbrainsVersionAccessors; }

    }

    public static class OrgJetbrainsVersionAccessors extends VersionFactory  {

        public OrgJetbrainsVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: org.jetbrains.kotlinx (1.6.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getKotlinx() { return getVersion("org.jetbrains.kotlinx"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

    }

    public static class PluginAccessors extends PluginFactory {
        private final ComPluginAccessors baccForComPluginAccessors = new ComPluginAccessors(providers, config);
        private final KotlinPluginAccessors baccForKotlinPluginAccessors = new KotlinPluginAccessors(providers, config);
        private final NlPluginAccessors baccForNlPluginAccessors = new NlPluginAccessors(providers, config);
        private final OrgPluginAccessors baccForOrgPluginAccessors = new OrgPluginAccessors(providers, config);

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for kotlin2js to the plugin id 'kotlin2js'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getKotlin2js() { return createPlugin("kotlin2js"); }

        /**
         * Returns the group of bundles at plugins.com
         */
        public ComPluginAccessors getCom() { return baccForComPluginAccessors; }

        /**
         * Returns the group of bundles at plugins.kotlin
         */
        public KotlinPluginAccessors getKotlin() { return baccForKotlinPluginAccessors; }

        /**
         * Returns the group of bundles at plugins.nl
         */
        public NlPluginAccessors getNl() { return baccForNlPluginAccessors; }

        /**
         * Returns the group of bundles at plugins.org
         */
        public OrgPluginAccessors getOrg() { return baccForOrgPluginAccessors; }

    }

    public static class ComPluginAccessors extends PluginFactory {
        private final ComGithubPluginAccessors baccForComGithubPluginAccessors = new ComGithubPluginAccessors(providers, config);

        public ComPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of bundles at plugins.com.github
         */
        public ComGithubPluginAccessors getGithub() { return baccForComGithubPluginAccessors; }

    }

    public static class ComGithubPluginAccessors extends PluginFactory {
        private final ComGithubBenPluginAccessors baccForComGithubBenPluginAccessors = new ComGithubBenPluginAccessors(providers, config);

        public ComGithubPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of bundles at plugins.com.github.ben
         */
        public ComGithubBenPluginAccessors getBen() { return baccForComGithubBenPluginAccessors; }

    }

    public static class ComGithubBenPluginAccessors extends PluginFactory {
        private final ComGithubBenManesPluginAccessors baccForComGithubBenManesPluginAccessors = new ComGithubBenManesPluginAccessors(providers, config);

        public ComGithubBenPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of bundles at plugins.com.github.ben.manes
         */
        public ComGithubBenManesPluginAccessors getManes() { return baccForComGithubBenManesPluginAccessors; }

    }

    public static class ComGithubBenManesPluginAccessors extends PluginFactory {

        public ComGithubBenManesPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for com.github.ben.manes.versions to the plugin id 'com.github.ben-manes.versions'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getVersions() { return createPlugin("com.github.ben.manes.versions"); }

    }

    public static class KotlinPluginAccessors extends PluginFactory  implements PluginNotationSupplier{
        private final KotlinAndroidPluginAccessors baccForKotlinAndroidPluginAccessors = new KotlinAndroidPluginAccessors(providers, config);
        private final KotlinDcePluginAccessors baccForKotlinDcePluginAccessors = new KotlinDcePluginAccessors(providers, config);
        private final KotlinNativePluginAccessors baccForKotlinNativePluginAccessors = new KotlinNativePluginAccessors(providers, config);
        private final KotlinPlatformPluginAccessors baccForKotlinPlatformPluginAccessors = new KotlinPlatformPluginAccessors(providers, config);

        public KotlinPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for kotlin to the plugin id 'kotlin'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> asProvider() { return createPlugin("kotlin"); }

            /**
             * Creates a plugin provider for kotlin.kapt to the plugin id 'kotlin-kapt'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getKapt() { return createPlugin("kotlin.kapt"); }

            /**
             * Creates a plugin provider for kotlin.multiplatform to the plugin id 'kotlin-multiplatform'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getMultiplatform() { return createPlugin("kotlin.multiplatform"); }

            /**
             * Creates a plugin provider for kotlin.parcelize to the plugin id 'kotlin-parcelize'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getParcelize() { return createPlugin("kotlin.parcelize"); }

            /**
             * Creates a plugin provider for kotlin.scripting to the plugin id 'kotlin-scripting'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getScripting() { return createPlugin("kotlin.scripting"); }

        /**
         * Returns the group of bundles at plugins.kotlin.android
         */
        public KotlinAndroidPluginAccessors getAndroid() { return baccForKotlinAndroidPluginAccessors; }

        /**
         * Returns the group of bundles at plugins.kotlin.dce
         */
        public KotlinDcePluginAccessors getDce() { return baccForKotlinDcePluginAccessors; }

        /**
         * Returns the group of bundles at plugins.kotlin.native
         */
        public KotlinNativePluginAccessors getNative() { return baccForKotlinNativePluginAccessors; }

        /**
         * Returns the group of bundles at plugins.kotlin.platform
         */
        public KotlinPlatformPluginAccessors getPlatform() { return baccForKotlinPlatformPluginAccessors; }

    }

    public static class KotlinAndroidPluginAccessors extends PluginFactory  implements PluginNotationSupplier{

        public KotlinAndroidPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for kotlin.android to the plugin id 'kotlin-android'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> asProvider() { return createPlugin("kotlin.android"); }

            /**
             * Creates a plugin provider for kotlin.android.extensions to the plugin id 'kotlin-android-extensions'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getExtensions() { return createPlugin("kotlin.android.extensions"); }

    }

    public static class KotlinDcePluginAccessors extends PluginFactory {

        public KotlinDcePluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for kotlin.dce.js to the plugin id 'kotlin-dce-js'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getJs() { return createPlugin("kotlin.dce.js"); }

    }

    public static class KotlinNativePluginAccessors extends PluginFactory {

        public KotlinNativePluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for kotlin.native.cocoapods to the plugin id 'kotlin-native-cocoapods'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getCocoapods() { return createPlugin("kotlin.native.cocoapods"); }

            /**
             * Creates a plugin provider for kotlin.native.performance to the plugin id 'kotlin-native-performance'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getPerformance() { return createPlugin("kotlin.native.performance"); }

    }

    public static class KotlinPlatformPluginAccessors extends PluginFactory {

        public KotlinPlatformPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for kotlin.platform.android to the plugin id 'kotlin-platform-android'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getAndroid() { return createPlugin("kotlin.platform.android"); }

            /**
             * Creates a plugin provider for kotlin.platform.common to the plugin id 'kotlin-platform-common'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getCommon() { return createPlugin("kotlin.platform.common"); }

            /**
             * Creates a plugin provider for kotlin.platform.js to the plugin id 'kotlin-platform-js'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getJs() { return createPlugin("kotlin.platform.js"); }

            /**
             * Creates a plugin provider for kotlin.platform.jvm to the plugin id 'kotlin-platform-jvm'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getJvm() { return createPlugin("kotlin.platform.jvm"); }

    }

    public static class NlPluginAccessors extends PluginFactory {
        private final NlLittlerobotsPluginAccessors baccForNlLittlerobotsPluginAccessors = new NlLittlerobotsPluginAccessors(providers, config);

        public NlPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of bundles at plugins.nl.littlerobots
         */
        public NlLittlerobotsPluginAccessors getLittlerobots() { return baccForNlLittlerobotsPluginAccessors; }

    }

    public static class NlLittlerobotsPluginAccessors extends PluginFactory {
        private final NlLittlerobotsVersionPluginAccessors baccForNlLittlerobotsVersionPluginAccessors = new NlLittlerobotsVersionPluginAccessors(providers, config);

        public NlLittlerobotsPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of bundles at plugins.nl.littlerobots.version
         */
        public NlLittlerobotsVersionPluginAccessors getVersion() { return baccForNlLittlerobotsVersionPluginAccessors; }

    }

    public static class NlLittlerobotsVersionPluginAccessors extends PluginFactory {
        private final NlLittlerobotsVersionCatalogPluginAccessors baccForNlLittlerobotsVersionCatalogPluginAccessors = new NlLittlerobotsVersionCatalogPluginAccessors(providers, config);

        public NlLittlerobotsVersionPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of bundles at plugins.nl.littlerobots.version.catalog
         */
        public NlLittlerobotsVersionCatalogPluginAccessors getCatalog() { return baccForNlLittlerobotsVersionCatalogPluginAccessors; }

    }

    public static class NlLittlerobotsVersionCatalogPluginAccessors extends PluginFactory {

        public NlLittlerobotsVersionCatalogPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for nl.littlerobots.version.catalog.update to the plugin id 'nl.littlerobots.version-catalog-update'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getUpdate() { return createPlugin("nl.littlerobots.version.catalog.update"); }

    }

    public static class OrgPluginAccessors extends PluginFactory {
        private final OrgJetbrainsPluginAccessors baccForOrgJetbrainsPluginAccessors = new OrgJetbrainsPluginAccessors(providers, config);

        public OrgPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of bundles at plugins.org.jetbrains
         */
        public OrgJetbrainsPluginAccessors getJetbrains() { return baccForOrgJetbrainsPluginAccessors; }

    }

    public static class OrgJetbrainsPluginAccessors extends PluginFactory {
        private final OrgJetbrainsKotlinPluginAccessors baccForOrgJetbrainsKotlinPluginAccessors = new OrgJetbrainsKotlinPluginAccessors(providers, config);

        public OrgJetbrainsPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of bundles at plugins.org.jetbrains.kotlin
         */
        public OrgJetbrainsKotlinPluginAccessors getKotlin() { return baccForOrgJetbrainsKotlinPluginAccessors; }

    }

    public static class OrgJetbrainsKotlinPluginAccessors extends PluginFactory {
        private final OrgJetbrainsKotlinAndroidPluginAccessors baccForOrgJetbrainsKotlinAndroidPluginAccessors = new OrgJetbrainsKotlinAndroidPluginAccessors(providers, config);
        private final OrgJetbrainsKotlinMultiplatformPluginAccessors baccForOrgJetbrainsKotlinMultiplatformPluginAccessors = new OrgJetbrainsKotlinMultiplatformPluginAccessors(providers, config);
        private final OrgJetbrainsKotlinNativePluginAccessors baccForOrgJetbrainsKotlinNativePluginAccessors = new OrgJetbrainsKotlinNativePluginAccessors(providers, config);
        private final OrgJetbrainsKotlinPlatformPluginAccessors baccForOrgJetbrainsKotlinPlatformPluginAccessors = new OrgJetbrainsKotlinPlatformPluginAccessors(providers, config);
        private final OrgJetbrainsKotlinPluginPluginAccessors baccForOrgJetbrainsKotlinPluginPluginAccessors = new OrgJetbrainsKotlinPluginPluginAccessors(providers, config);

        public OrgJetbrainsKotlinPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for org.jetbrains.kotlin.js to the plugin id 'org.jetbrains.kotlin.js'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getJs() { return createPlugin("org.jetbrains.kotlin.js"); }

            /**
             * Creates a plugin provider for org.jetbrains.kotlin.jvm to the plugin id 'org.jetbrains.kotlin.jvm'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getJvm() { return createPlugin("org.jetbrains.kotlin.jvm"); }

            /**
             * Creates a plugin provider for org.jetbrains.kotlin.kapt to the plugin id 'org.jetbrains.kotlin.kapt'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getKapt() { return createPlugin("org.jetbrains.kotlin.kapt"); }

        /**
         * Returns the group of bundles at plugins.org.jetbrains.kotlin.android
         */
        public OrgJetbrainsKotlinAndroidPluginAccessors getAndroid() { return baccForOrgJetbrainsKotlinAndroidPluginAccessors; }

        /**
         * Returns the group of bundles at plugins.org.jetbrains.kotlin.multiplatform
         */
        public OrgJetbrainsKotlinMultiplatformPluginAccessors getMultiplatform() { return baccForOrgJetbrainsKotlinMultiplatformPluginAccessors; }

        /**
         * Returns the group of bundles at plugins.org.jetbrains.kotlin.native
         */
        public OrgJetbrainsKotlinNativePluginAccessors getNative() { return baccForOrgJetbrainsKotlinNativePluginAccessors; }

        /**
         * Returns the group of bundles at plugins.org.jetbrains.kotlin.platform
         */
        public OrgJetbrainsKotlinPlatformPluginAccessors getPlatform() { return baccForOrgJetbrainsKotlinPlatformPluginAccessors; }

        /**
         * Returns the group of bundles at plugins.org.jetbrains.kotlin.plugin
         */
        public OrgJetbrainsKotlinPluginPluginAccessors getPlugin() { return baccForOrgJetbrainsKotlinPluginPluginAccessors; }

    }

    public static class OrgJetbrainsKotlinAndroidPluginAccessors extends PluginFactory  implements PluginNotationSupplier{

        public OrgJetbrainsKotlinAndroidPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for org.jetbrains.kotlin.android to the plugin id 'org.jetbrains.kotlin.android'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> asProvider() { return createPlugin("org.jetbrains.kotlin.android"); }

            /**
             * Creates a plugin provider for org.jetbrains.kotlin.android.extensions to the plugin id 'org.jetbrains.kotlin.android.extensions'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getExtensions() { return createPlugin("org.jetbrains.kotlin.android.extensions"); }

    }

    public static class OrgJetbrainsKotlinMultiplatformPluginAccessors extends PluginFactory  implements PluginNotationSupplier{

        public OrgJetbrainsKotlinMultiplatformPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for org.jetbrains.kotlin.multiplatform to the plugin id 'org.jetbrains.kotlin.multiplatform'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> asProvider() { return createPlugin("org.jetbrains.kotlin.multiplatform"); }

            /**
             * Creates a plugin provider for org.jetbrains.kotlin.multiplatform.pm20 to the plugin id 'org.jetbrains.kotlin.multiplatform.pm20'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getPm20() { return createPlugin("org.jetbrains.kotlin.multiplatform.pm20"); }

    }

    public static class OrgJetbrainsKotlinNativePluginAccessors extends PluginFactory {

        public OrgJetbrainsKotlinNativePluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for org.jetbrains.kotlin.native.cocoapods to the plugin id 'org.jetbrains.kotlin.native.cocoapods'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getCocoapods() { return createPlugin("org.jetbrains.kotlin.native.cocoapods"); }

            /**
             * Creates a plugin provider for org.jetbrains.kotlin.native.performance to the plugin id 'org.jetbrains.kotlin.native.performance'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getPerformance() { return createPlugin("org.jetbrains.kotlin.native.performance"); }

    }

    public static class OrgJetbrainsKotlinPlatformPluginAccessors extends PluginFactory {

        public OrgJetbrainsKotlinPlatformPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for org.jetbrains.kotlin.platform.android to the plugin id 'org.jetbrains.kotlin.platform.android'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getAndroid() { return createPlugin("org.jetbrains.kotlin.platform.android"); }

            /**
             * Creates a plugin provider for org.jetbrains.kotlin.platform.common to the plugin id 'org.jetbrains.kotlin.platform.common'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getCommon() { return createPlugin("org.jetbrains.kotlin.platform.common"); }

            /**
             * Creates a plugin provider for org.jetbrains.kotlin.platform.js to the plugin id 'org.jetbrains.kotlin.platform.js'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getJs() { return createPlugin("org.jetbrains.kotlin.platform.js"); }

            /**
             * Creates a plugin provider for org.jetbrains.kotlin.platform.jvm to the plugin id 'org.jetbrains.kotlin.platform.jvm'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getJvm() { return createPlugin("org.jetbrains.kotlin.platform.jvm"); }

    }

    public static class OrgJetbrainsKotlinPluginPluginAccessors extends PluginFactory {

        public OrgJetbrainsKotlinPluginPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for org.jetbrains.kotlin.plugin.parcelize to the plugin id 'org.jetbrains.kotlin.plugin.parcelize'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getParcelize() { return createPlugin("org.jetbrains.kotlin.plugin.parcelize"); }

            /**
             * Creates a plugin provider for org.jetbrains.kotlin.plugin.scripting to the plugin id 'org.jetbrains.kotlin.plugin.scripting'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getScripting() { return createPlugin("org.jetbrains.kotlin.plugin.scripting"); }

    }

}
