package api.longpoll.bots.methods;

import org.jsoup.Connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Implementation of POST HTTP method. Used in loading files.
 *
 * @param <Response> response type.
 */
public abstract class PostMethod<Response> extends Method<Response> {
    /**
     * File to be uploaded.
     */
    private File file;

    @Override
    protected Connection.Response execute(Connection connection) throws IOException {
        try (InputStream inputStream = new FileInputStream(file)) {
            connection.data(getType(), file.getName(), inputStream);
            return super.execute(connection);
        }
    }

    @Override
    protected Connection.Method getMethod() {
        return Connection.Method.POST;
    }

    /**
     * Gets file type.
     *
     * @return file type.
     */
    protected abstract String getType();

    public File getFile() {
        return file;
    }

    public PostMethod<Response> setFile(File file) {
        this.file = file;
        return this;
    }
}
