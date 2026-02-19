@PostMapping("/login")
public String procesarLogin(
    @RequestParam String username, 
    @RequestParam String password, 
    HttpSession session
) {
    if ("admin".equals(username) && "1234".equals(password)) {
        session.setAttribute("user", username);
        return "redirect:/home"; // Redirección lógica
    }
    return "redirect:/login?error";
}