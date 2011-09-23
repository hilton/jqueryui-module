package templates;

import groovy.lang.Closure;
import play.Logger;
import play.exceptions.TagInternalException;
import play.exceptions.TemplateExecutionException;
import play.exceptions.TemplateNotFoundException;
import play.templates.*;
import play.vfs.VirtualFile;

import java.io.PrintWriter;
import java.util.Map;

public class DemoTags extends FastTags {

    public static void _includeVerbatim(Map<?, ?> args, Closure body, PrintWriter out, GroovyTemplate.ExecutableTemplate t, int fromLine) {
        try {
            final String path = args.get("arg").toString();
            final BaseTemplate template = (BaseTemplate) TemplateLoader.load(path);
            final String contents = template.source;
            out.print(JavaExtensions.escapeHtml(contents));
        } catch (TemplateNotFoundException e) {
            throw new TemplateNotFoundException(e.getPath(), t.template, fromLine);
        }
    }
}
