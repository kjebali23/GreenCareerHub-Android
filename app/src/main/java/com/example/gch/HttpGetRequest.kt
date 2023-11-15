import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class HttpGetRequest(private val listener: OnTaskCompleted) {

    interface OnTaskCompleted {
        fun onTaskCompleted(result: String)
        fun onTaskError(error: Exception)
    }

    fun execute(url: String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val result = makeRequest(url)
                withContext(Dispatchers.Main) {
                    listener.onTaskCompleted(result)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    listener.onTaskError(e)
                }
            }
        }
    }

    private fun makeRequest(url: String): String {
        return try {
            val connection = URL(url).openConnection() as HttpURLConnection
            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            val result = StringBuilder()
            var line: String?

            while (reader.readLine().also { line = it } != null) {
                result.append(line)
            }

            connection.disconnect()
            result.toString()
        } catch (e: Exception) {
            throw e
        }
    }
}
