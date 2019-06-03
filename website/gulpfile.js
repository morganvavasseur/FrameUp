var gulp = require('gulp'),
	sass = require('gulp-sass'),
	postcss = require('gulp-postcss'),
	autoprefixer = require('autoprefixer'),
	cssnano = require('cssnano'),
	sourcemaps = require('gulp-sourcemaps'),
	del = require('del'), 
	browserSync = require('browser-sync').create();

var browsers = ['last 2 versions'],
	reload = browserSync.reload;


// Paths to folder & files
var paths = {
	styles: {
		src: "src/scss/**/*.scss",
		dist: "src/css",
	},
	img : {
		src: "src/images",
		dist: "dist/images",
	},
	html: "src/*.html",
	js: "src/js/**/*.js",
};

// Compile scss into css
function style() {
	// Where is my css file
	return gulp.src(paths.styles.src)
	// Initialize sourcemaps before compilation starts
	.pipe(sourcemaps.init())
	// Pass that file through sass compiler
	.pipe(sass().on('error', sass.logError))
	// Use postcss with autoprefixer and compress the compiled file using cssnano
	.pipe(postcss([autoprefixer(browsers), cssnano()]))
	// Add/write the sourcemaps
	.pipe(sourcemaps.write())
	// Where do I save the compiled CSS ?
	.pipe(gulp.dest(paths.styles.dist))
	// Stream changes to all browser
	.pipe(browserSync.stream());
}

// Watch function
function watch() {
	browserSync.init({
		server: {
			baseDir: 'src'
		}
	});
	gulp.watch(paths.styles.src, style);
	gulp.watch(paths.html).on('change', reload);
	gulp.watch(paths.js).on('change', reload);
}

// Expose the task
module.exports = {
	style,
	watch,
}

var build = gulp.parallel(style, watch);

gulp.task('default', build);